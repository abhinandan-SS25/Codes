# Generated by Django 3.2.9 on 2021-11-24 07:17

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('auctions', '0013_remove_listings_c_price'),
    ]

    operations = [
        migrations.AddField(
            model_name='listings',
            name='c_price',
            field=models.FloatField(blank=True, null=True),
        ),
    ]
