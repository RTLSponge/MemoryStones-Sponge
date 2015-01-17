package au.id.rleach.guice;

import com.google.inject.AbstractModule;
import com.google.inject.MembersInjector;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * Created by Ryan on 12/01/2015.
 */
public class LoggingModule extends AbstractModule {
    @Override
    protected void configure() {
        bindListener(Matchers.any(), new Slf4jTypeListener());
    }
    private class Slf4jTypeListener implements TypeListener{

        @Override
        public <I> void hear(final TypeLiteral<I> type, final TypeEncounter<I> encounter) {
            Class<?> clazz = type.getRawType();
            while(clazz != null){
                for(final Field field : clazz.getDeclaredFields()){
                    if(Logger.class.equals(field.getType()) && field.isAnnotationPresent(InjectLogger.class)) {
                        encounter.register(new Slf4jMembersInjector<I>(field));
                    }
                }
                clazz = clazz.getSuperclass();
            }
        }
    }

    class Slf4jMembersInjector<T> implements MembersInjector<T>{
        private final Field field;
        private final Logger logger;

        Slf4jMembersInjector(Field field) {
            this.field = field;
            this.logger = LoggerFactory.getLogger(field.getDeclaringClass());
            field.setAccessible(true);
        }

        public void injectMembers(T t){
            try {
                field.set(t, logger);
            } catch (IllegalAccessException e){
                throw new RuntimeException(e);
            }
        }
    }
}
