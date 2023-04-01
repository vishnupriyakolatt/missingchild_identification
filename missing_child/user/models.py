from django.db import models

# Create your models here
class User(models.Model):
    user_id = models.AutoField(primary_key=True)
    username = models.CharField(max_length=50)
    u_gender = models.CharField(max_length=20)
    u_address = models.CharField(max_length=50)
    u_email = models.CharField(max_length=20)
    u_phno = models.CharField(max_length=30)
    password = models.CharField(max_length=20)
    u_pin = models.CharField(max_length=50)
    u_state = models.CharField(max_length=50)
    u_country = models.CharField(max_length=50)

    class Meta:
        managed = False
        db_table = 'user'

