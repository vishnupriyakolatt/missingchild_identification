from django.conf.urls import url
from complaint import views

urlpatterns = [
    url('viewcomplaint/',views.view_complaint),
    url('replypost/(?P<abc>\w+)',views.reply_post,name='post_reply'),
    url('android/',views.post_complaint.as_view()),
]