from django.shortcuts import render
from missingcase.models import Missingcase
from django.core.files.storage import FileSystemStorage
from django.http import HttpResponse
from missingcase.serializer import android
from  rest_framework.views import APIView,Response
from missing_child import settings
from django.http import HttpResponseRedirect
from PIL import Image
from io import BytesIO
import base64
from django.views.decorators.csrf import csrf_exempt

import string
from django.http import JsonResponse
from django.db import connection
from MySQLdb._mysql import result
from luxand import luxand



class facedata:
    client = luxand("36f0a9d16c554e87bd09d024842e1b50")

    obj = Missingcase.objects.filter(m_status='pending')

    for x in obj:
        # imgpath = settings.BASE_DIR + settings.STATIC_URL + "faces/"+ str(x) +".jpg"
        img = x.m_img
        nm = x.mp_name
        idd = x.m_id
        i = str(idd)


        # print(img+"#"+nm+"#"+i+"#")

        imgpath = settings.BASE_DIR + settings.STATIC_URL + img
        print(imgpath)
        client.add_person(name=i, photos=[imgpath])
        print('added')



def register_missingcase(request):
    dd = request.session["uid"]
    ds = request.session["uid"]
    if request.method=="POST":
        obj=Missingcase()
        obj.mp_name=request.POST.get('mp_name')
        obj.m_name=request.POST.get('pst')
        obj.m_datemis = request.POST.get('dms')
        obj.m_datereport= request.POST.get('dre')
        obj.m_age=request.POST.get('age')
        obj.m_gender= request.POST.get('gen')
        obj.m_height= request.POST.get('hg')
        obj.m_complexion = request.POST.get('co')
        obj.m_identimark= request.POST.get('identity')
        obj.m_detail= request.POST.get('wear')
        obj.m_status="pending"
        obj.m_address = request.POST.get('address')
        img = request.FILES['img']
        ff = FileSystemStorage()
        filename = ff.save(img.name, img)
        obj.m_img = img.name
        # obj.p_id = dd
        obj.user_id=dd
        obj.type = 'police'
        obj.save()
    return render(request,'missingcase/registermissingcase.html')


def view_missingcase(request):
    o=Missingcase.objects.filter(m_status="pending")
    context={
        'objval':o,
    }
    if request.method=="POST":
        ty=request.POST.get('ty')
        obb=Missingcase.objects.filter(type=ty)
        conte={
            'objval':obb
        }
        return render(request,'missingcase/viewmissingcase.html',conte)
    return render(request, 'missingcase/viewmissingcase.html',context)







class case_register(APIView):
    def get(self,request):
        obj = Missingcase.objects.all()
        ser = android(obj,many=True)
        return Response(ser.data)

    def post(self,request):
        obj=Missingcase()
        obj.mp_name=request.data['mp_name']
        obj.m_name=request.data['m_name']
        obj.m_age = request.data['m_age']
        obj.m_gender = request.data['m_gender']
        obj.m_datemis = request.data['m_datemis']
        obj.m_datereport = request.data['m_datereport']
        obj.m_height = request.data['m_height']
        obj.m_complexion = request.data['m_complexion']
        obj.m_identimark = request.data['m_identimark']
        obj.m_detail = request.data['m_detail']
        obj.m_address = request.data['m_address']
        obj.m_status = request.data['m_status']
        obj.m_img = ""
        obj.type= "user"
        obj.user_id= request.data['user_id']
        obj.save()
        idd = str(obj.m_id)
        i = idd + ".jpg"

        f = request.data['m_img']

        im = Image.open(BytesIO(base64.b64decode(f)))
        imgpath = settings.BASE_DIR + settings.STATIC_URL + i
        im.save(imgpath)

        ob = Missingcase.objects.get(m_id=idd)
        ob.m_img = i
        ob.save()
        return HttpResponse("yesss")









def approve(request,idd):
    # obj = MissingCaseDetails.objects.get(id=idd)
    # obj.status='Approved'
    # obj.save()

    request.session['mid'] = idd
    return HttpResponseRedirect('/missingcase/recg/')


def reject(request,idd):
    obj = Missingcase.objects.get(m_id=idd)
    obj.m_status= 'Rejected'
    obj.save()
    return view_missingcase(request)
    # return render(request,'missingcase/viewmissingcase.html')












from missingreport.models import ReportMissingcase



def recg(request):

    # client.add_person(name="Brad Pitt", photos=["https://dashboard.luxand.cloud/img/brad-pitt.jpg"])
    if request.method == "POST":
        # imgpath = settings.BASE_DIR + settings.STATIC_URL + "/faces/4.jpg"

        myfile = request.FILES['img']
        fs = FileSystemStorage()
        filename = fs.save(myfile.name, myfile)
        image = myfile.name

        imgpath = settings.BASE_DIR + settings.STATIC_URL + image
        result = facedata.client.recognize(photo=imgpath)

        print(len(result))
        if len(result)>0:
            try:
                print(result)
                print(type(result))
                res=result[0]
                print('hello')
                print(res)
                print(type(res))
                c=res['name']
                c=int(c)
                print('dddddd')
                print(c)
                # p = '"'+result+'"'
                # print(result)
                # res = result.replace("[", "")
                # res = res.replace("]", "")
                # res=result[0]

                # print(res[0])
                # res = res.replace("'", '"')
                # dt = json.loads(res)
                # a = dt['name']
                # print(a)
                # b = a.replace("'", "")
                # c = int(b)
                # print(c)
                # c=4

                o = ReportMissingcase.objects.get(report_id=c)
                img1 = o.report_img
                o.m_status = 'found'
                o.save()

                # return HttpResponse(result)
                context = {
                    'objval':result,
                    'img':image,
                    "case":img1,
                }
                return render(request, 'missingcase/solve.html',context)
            except:
                context = {
                    "case": "No match Found",
                }
                return render(request, 'missingcase/solve.html', context)
                # /return HttpResponse("No Matching Found")
        else:
            context = {
                "res": "No match Found",
            }
            return render(request, 'missingcase/solve.html', context)
    return render(request,'missingcase/solve.html')
















