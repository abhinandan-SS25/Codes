# Generated by Django 3.2.9 on 2021-11-24 06:33

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('auctions', '0005_auto_20211124_1146'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='categories',
            name='listing_title',
        ),
        migrations.AlterField(
            model_name='categories',
            name='category',
            field=models.ForeignKey(on_delete=django.db.models.deletion.PROTECT, related_name='Item', to='auctions.listings'),
        ),
        migrations.AlterField(
            model_name='listings',
            name='category',
            field=models.CharField(max_length=60),
        ),
    ]