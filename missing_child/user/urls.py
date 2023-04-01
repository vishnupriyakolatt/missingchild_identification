from django.conf.urls import url
from user import views

urlpatterns = [
    url('viewuser/',views.viewuser),
    url('android/', views.user.as_view()),
]