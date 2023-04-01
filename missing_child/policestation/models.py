from django.db import models
#eate your models here.

class Policestation(models.Model):
    p_id = models.AutoField(primary_key=True)
    p_name = models.CharField(max_length=50)
    p_address = models.CharField(max_length=50)
    p_pin = models.IntegerField()
    p_state = models.CharField(max_length=50)
    p_country = models.CharField(max_length=50)
    phone_no = models.CharField(max_length=20)
    p_email = models.CharField(max_length=50)
    p_img = models.CharField(max_length=100)
    password = models.CharField(max_length=20)

    class Meta:
        managed = False
        db_table = 'policestation'
