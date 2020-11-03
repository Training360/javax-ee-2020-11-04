package bank;

import liquibase.integration.cdi.CDILiquibaseConfig;
import liquibase.integration.cdi.annotations.LiquibaseType;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;

import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;
import java.sql.SQLException;

@Dependent
public class LiquibaseProducer {

    @Resource(mappedName = "java:/jdbc/BankDS")
    private DataSource dataSource;

    @Produces  @LiquibaseType
    public CDILiquibaseConfig createConfig()  {
        System.out.println("config");
        CDILiquibaseConfig  config  =  new  CDILiquibaseConfig();
        config.setChangeLog("liquibase/changeLog.xml");
        return  config;
    }

    @Produces  @LiquibaseType
    public  DataSource  createDataSource()  throws SQLException {
        return dataSource;
    }

    @Produces
    @LiquibaseType
    public ResourceAccessor create()  {
        return  new ClassLoaderResourceAccessor(getClass().getClassLoader());
    }
}
