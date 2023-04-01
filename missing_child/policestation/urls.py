from django.conf.urls import url
from complaint import views
from policestation import views
urlpatterns =[
       url('registrationform/',views.register_police),
]
