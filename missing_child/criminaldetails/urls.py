from django.conf.urls import url
from criminaldetails import views

urlpatterns = [
    url('criminaldetails_manage/',views.criminaldetails_manage),
    url('viewcriminaldetails/',views.view_criminal),
    url('updatecriminal/(?P<idd>\w+)', views.update_criminal,name='update_criminal'),
    url('deletecriminal/(?P<idd>\w+)', views.delete_criminal,name='delete_criminal'),
    url('android/',views.view_criminaldetails.as_view()),
]