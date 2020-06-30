package project.ramezreda.resumy.notifications

interface INotification {
    fun showInsertToast(id: Long?)
    fun showUpdateToast(result: Int?)
}