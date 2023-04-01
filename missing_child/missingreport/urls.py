from django.conf.urls import url
from missingreport import views
urlpatterns =[

       url('viewmissingreport//',views.missingcase_report),
       url(r'^android/', views.report.as_view()),

]