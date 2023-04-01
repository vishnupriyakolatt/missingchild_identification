from rest_framework import serializers
from missingcase.models import Missingcase
class android(serializers.ModelSerializer):
    class Meta:
        model=Missingcase
        fields='__all__'
