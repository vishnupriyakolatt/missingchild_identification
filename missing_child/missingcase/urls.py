from django.conf.urls import url
from missingcase import views
#
urlpatterns =[

       url('register_missingcase/',views.register_missingcase),
       url('recg/',views.recg,name="recg"),
       url('approve/(?P<idd>\w+)',views.approve,name="approve"),
       url('reject/(?P<idd>\w+)',views.reject,name="reject"),
       url('view_missingcase/', views.view_missingcase),
       url('android/',views.case_register.as_view()),
]