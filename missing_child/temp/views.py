from django.shortcuts import render
from django.shortcuts import render
def police_site(request):
    return render(request,'temp/police_site.html')



def index_site(request):

    return render(request,'temp/index.html')



def police_home(request):
    return render(request, 'temp/policehome.html')


