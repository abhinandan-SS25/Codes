# Generated by Django 3.2.9 on 2021-12-06 16:23

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('network', '0004_auto_20211206_2146'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='post',
            name='post_likes',
        ),
    ]
