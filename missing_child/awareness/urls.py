from django.conf.urls import url
from awareness import views

urlpatterns=[
    url('add_awareness/',views.add_awareness),
    url('android/',views.awareness_view.as_view()),
]