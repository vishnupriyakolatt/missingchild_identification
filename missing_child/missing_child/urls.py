"""missing_child URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/3.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path
from temp import views
from django.conf.urls import url,include

urlpatterns = [
    path('admin/', admin.site.urls),
    url('awareness/',include('awareness.urls')),
    url('complaint/',include('complaint.urls')),
    url('criminaldetails/',include('criminaldetails.urls')),
    url('emergency/',include('emergency.urls')),
    url('login/',include('login.urls')),
    url('missingcase/',include('missingcase.urls')),
    url('report/',include('missingreport.urls')),
    url('policestation/',include('policestation.urls')),
    url('user/',include('user.urls')),
    url('temp/',include('temp.urls')),
    url('$',views.index_site)
]
