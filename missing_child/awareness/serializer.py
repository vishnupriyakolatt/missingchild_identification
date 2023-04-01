from rest_framework import serializers
from awareness.models import Awarness


class android(serializers.ModelSerializer):
    class Meta:
        model=Awarness
        fields='__all__'

