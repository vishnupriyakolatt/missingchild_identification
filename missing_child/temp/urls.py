from django.conf.urls import url
from temp import views
urlpatterns=[
       url('police_site/',views.police_site),
       url('indexpage/',views.index_site),
       url('policehome/',views.police_home),
]