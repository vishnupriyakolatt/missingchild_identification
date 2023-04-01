from django.db import models

class Missingcase(models.Model):
    m_id = models.AutoField(primary_key=True)
    mp_name= models.CharField(max_length=50)
    m_name = models.CharField(max_length=50)
    m_age = models.IntegerField()
    m_gender = models.CharField(max_length=20)
    m_datemis = models.DateField()
    m_datereport = models.DateField()
    m_height = models.IntegerField()
    m_complexion = models.CharField(max_length=50)
    m_identimark = models.CharField(max_length=50)
    m_detail = models.CharField(max_length=50)
    m_address = models.CharField(max_length=50)
    m_status = models.CharField(max_length=20)
    m_img = models.CharField(max_length=100)
    type = models.CharField(max_length=50)
    user_id = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'missingcase'

