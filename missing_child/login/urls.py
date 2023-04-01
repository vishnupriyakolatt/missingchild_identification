from django.conf.urls import url
from login import views

urlpatterns = [
    url('login/', views.login),
    url('android/',views.login1.as_view()),
    url('android1/',views.forgot.as_view()),
    url('forgot/',views.forgot1),
]
