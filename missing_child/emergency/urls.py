from django.conf.urls import url
from emergency import views

urlpatterns =[

       url('add_emergencyno/',views.add_emergencyno),
       url('android/',views.view_emergency.as_view()),
]