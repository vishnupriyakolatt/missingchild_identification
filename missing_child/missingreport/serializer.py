from rest_framework import serializers
from missingreport.models import ReportMissingcase
class android(serializers.ModelSerializer):
    class Meta:
        model=ReportMissingcase
        fields='__all__'
