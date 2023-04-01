from django.shortcuts import render
from missingreport.models import ReportMissingcase
from django.http import HttpResponse
from missing_child import settings
from django.http import HttpResponseRedirect
from PIL import Image
from io import BytesIO
import base64
from missingreport.serializer import android
from  rest_framework.views import APIView,Response

def missingcase_report(request):
    o=ReportMissingcase.objects.all()
    context={
        'objval':o,
    }
    return render(request, 'missingreport/viewmissingreport.html',context)

import datetime

class report(APIView):
    def get(self,request):
        obj = ReportMissingcase.objects.all()
        ser = android(obj,many=True)
        return Response(ser.data)


    def post(self,request):
        obj=ReportMissingcase()
        obj.report_date=request.data['report_date']
        obj.report_time=datetime.datetime.now().time()
        obj.report_loc = request.data['report_loc']
        # obj.report_img= request.data['report_img']

        obj.report_img = ""
        # obj.type = "user"
        obj.user_id = request.data['user']
        obj.save()
        idd = str(obj.report_id)
        i = idd + ".jpg"

        f = request.data['report_img']

        im = Image.open(BytesIO(base64.b64decode(f)))
        imgpath = settings.BASE_DIR + settings.STATIC_URL + i
        im.save(imgpath)

        ob = ReportMissingcase.objects.get(report_id=idd)
        ob.report_img = i

        # obj.user_id= request.data['user']
        ob.save()
        return HttpResponse("yesss")



