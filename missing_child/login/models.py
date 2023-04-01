from django.db import models

# Create your models here.
class Login(models.Model):
    l_id = models.AutoField(primary_key=True)
    username = models.CharField(max_length=50)
    password = models.CharField(max_length=50)
    type = models.CharField(max_length=20)
    user_id = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'login'
