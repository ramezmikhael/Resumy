package project.ramezreda.resumy.ui.basicInfo

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_basic_info.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import project.ramezreda.resumy.utils.ImageConverter
import project.ramezreda.resumy.R
import project.ramezreda.resumy.databinding.FragmentBasicInfoBinding
import project.ramezreda.resumy.di.ApplicationContextModule
import project.ramezreda.resumy.di.DaggerAppComponent
import project.ramezreda.resumy.di.NotificationsModule
import project.ramezreda.resumy.notifications.INotification
import project.ramezreda.resumy.ui.BaseFragment
import javax.inject.Inject

class BasicInfoFragment : BaseFragment() {

    companion object {
        private const val REQUEST_SELECT_IMAGE_IN_ALBUM: Int = 1
    }

    @Inject
    lateinit var viewModel: BasicInfoViewModel
    @Inject
    lateinit var notification: INotification

    override fun getLayoutRes(): Int = R.layout.fragment_basic_info

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        initDagger()

        (binding as FragmentBasicInfoBinding).viewModel = viewModel

        initObservers()

        binding.root.imageViewPicture.setOnClickListener() {
            selectImageInAlbum()
        }

        return binding.root
    }

    private fun initDagger() {
        DaggerAppComponent.builder()
            .applicationContextModule(
                ApplicationContextModule(
                    requireContext().applicationContext as Application,
                    requireContext()
                )
            )
            .notificationsModule(NotificationsModule(requireContext()))
            .build()
            .inject(this)
    }

    private fun initObservers() {
        viewModel.basicInfo?.observe(viewLifecycleOwner, Observer {
            binding.root.editTextName.setText(it?.first()?.fullName)
            binding.root.editTextEmail.setText(it?.first()?.email)
            binding.root.editTextPhone.setText(it?.first()?.phone)

            val image = ImageConverter.byteArrayToBitmap(it?.first()?.picture)
            image?.let { bitmap ->
                binding.root.imageViewPicture.setImageBitmap(bitmap)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu_save, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_save) {
            fetchBasicInfo()
            val job = GlobalScope.async {
                viewModel.update(viewModel.basicInfo?.value?.first())
            }

            GlobalScope.launch(Dispatchers.Main) {
                val res = job.await()
                notification.showUpdateToast(res)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun fetchBasicInfo() {
        val entity = viewModel.basicInfo?.value?.first()
        entity?.fullName = binding.root.editTextName.text.toString()
        entity?.email = binding.root.editTextEmail.text.toString()
        entity?.phone = binding.root.editTextPhone.text.toString()
        entity?.picture =
            ImageConverter.bitmapToByteArray(binding.root.imageViewPicture.drawable.toBitmap())
    }

    private fun selectImageInAlbum() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        if (intent.resolveActivity(requireContext().packageManager) != null) {
            startActivityForResult(intent, REQUEST_SELECT_IMAGE_IN_ALBUM)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_SELECT_IMAGE_IN_ALBUM) {
            binding.root.imageViewPicture.setImageURI(data?.data)
            binding.root.imageViewPicture.tag = data?.data.toString()
            Log.d("uri", data?.data.toString())
        }
    }
}