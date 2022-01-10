# Generated by Django 3.2.9 on 2021-11-24 05:49

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('auctions', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='Categories',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('category', models.CharField(max_length=50)),
                ('listing_title', models.CharField(max_length=60)),
            ],
        ),
        migrations.CreateModel(
            name='Listings',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('status', models.CharField(choices=[('A', 'ACTIVE'), ('C', 'CLOSE')], max_length=1)),
                ('title', models.CharField(max_length=60)),
                ('description', models.TextField()),
                ('l_price', models.FloatField()),
                ('c_price', models.FloatField()),
                ('picture', models.URLField()),
                ('category', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='Category', to='auctions.categories')),
            ],
        ),
    ]