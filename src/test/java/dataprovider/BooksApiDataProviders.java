package dataprovider;

import org.testng.annotations.DataProvider;

public class BooksApiDataProviders {


    @DataProvider(name = "query_parameters")
    public Object[][] queryParamsData(){
        return new  Object[][]{
                {"non-fiction", 2},
                {"fiction", 1},
                {"fiction", 0}
        };
    }
}
