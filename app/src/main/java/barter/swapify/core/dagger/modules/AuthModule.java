package barter.swapify.core.dagger.modules;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Singleton;

import barter.swapify.core.network.ConnectionCheckerImpl;
import barter.swapify.features.auth.data.datasource.AuthRemoteDataSource;
import barter.swapify.features.auth.data.datasource.AuthRemoteDataSourceImpl;
import barter.swapify.features.auth.data.repositories.AuthRepositoryImpl;
import barter.swapify.features.auth.domain.repository.AuthRepository;
import barter.swapify.features.auth.presentation.pages.LoginPage;
import barter.swapify.features.auth.presentation.pages.LogoutPage;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

@Module
public abstract class AuthModule {

    @ContributesAndroidInjector
    abstract LoginPage contributeLoginPage();

    @ContributesAndroidInjector
    abstract LogoutPage contributeLogoutPage();

    @Provides
    @Singleton
    static AuthRepository provideAuthRepository(Context context) {
        return new AuthRepositoryImpl(new ConnectionCheckerImpl(context), provideAuthRemoteDataSource(context));
    }

    @Provides
    @Singleton
    static AuthRemoteDataSource provideAuthRemoteDataSource(Context context) {
        return new AuthRemoteDataSourceImpl(context, FirebaseAuth.getInstance(), FirebaseFirestore.getInstance());
    }

    @Provides
    @Singleton
    static CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }
}
