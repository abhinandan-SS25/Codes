# Generated by Django 3.2.9 on 2021-11-24 06:35

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('auctions', '0006_auto_20211124_1203'),
    ]

    operations = [
        migrations.AlterField(
            model_name='listings',
            name='c_price',
            field=models.FloatField(blank=True, null=True),
        ),
        migrations.AlterField(
            model_name='listings',
            name='picture',
            field=models.URLField(blank=True, null=True),
        ),
    ]
