/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web_Services;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Milind Ghiya
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(Web_Services.AddContestService.class);
        resources.add(Web_Services.CollegeNameService.class);
        resources.add(Web_Services.ContestService.class);
        resources.add(Web_Services.ContestUpdateService.class);
        resources.add(Web_Services.DeleteContestService.class);
        resources.add(Web_Services.Helloworld.class);
        resources.add(Web_Services.LoginService.class);
        resources.add(Web_Services.RegisterService.class);
        resources.add(Web_Services.UpdateProfileService.class);
        resources.add(Web_Services.UserInfoService.class);

    }

}
