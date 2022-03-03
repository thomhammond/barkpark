package com.barkpark.dependency;

import com.barkpark.activities.parks.GetParksActivity;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = { MapperModule.class })
public interface ServiceComponent {
    GetParksActivity provideGetParksActivity();
}

