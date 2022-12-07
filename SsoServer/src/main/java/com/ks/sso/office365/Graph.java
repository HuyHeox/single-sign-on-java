package com.ks.sso.office365;

import com.microsoft.graph.logger.DefaultLogger;
import com.microsoft.graph.logger.LoggerLevel;
import com.microsoft.graph.models.extensions.IGraphServiceClient;
import com.microsoft.graph.models.extensions.User;
import com.microsoft.graph.requests.extensions.GraphServiceClient;
import com.microsoft.graph.requests.extensions.ICalendarGroupCollectionPage;
import com.microsoft.graph.requests.extensions.IEventCollectionPage;
import com.microsoft.graph.requests.extensions.IGroupCollectionPage;

import java.util.LinkedList;
import java.util.List;

import com.microsoft.graph.models.extensions.CalendarGroup;
import com.microsoft.graph.models.extensions.Event;import com.microsoft.graph.options.Option;
import com.microsoft.graph.options.QueryOption;
/**
 * Graph
 */
public class Graph {

    private static IGraphServiceClient graphClient = null;
    private static SimpleAuthProvider authProvider = null;
    
    public static void getYourTeams(String accessToken) {
    	ensureGraphClient(accessToken);
    	IGraphServiceClient graphClient = GraphServiceClient.builder().authenticationProvider( authProvider ).buildClient();
    	System.out.println("graphClient "+ graphClient);
    	IGroupCollectionPage joinedTeams = graphClient.me().joinedTeams().buildRequest().get();
    	System.out.println("joinedTeams "+ joinedTeams.getRawObject());
    }

    public static SimpleAuthProvider ensureGraphClient(String accessToken) {
        if (graphClient == null) {
            // Create the auth provider
            authProvider = new SimpleAuthProvider(accessToken);

            // Create default logger to only log errors
            DefaultLogger logger = new DefaultLogger();
            logger.setLoggingLevel(LoggerLevel.DEBUG);

            // Build a Graph client
            graphClient = GraphServiceClient.builder()
                .authenticationProvider(authProvider)
                .logger(logger)
                .buildClient();
        }
        return authProvider;
    }

    public static User getUser(String accessToken) {
        ensureGraphClient(accessToken);

        // GET /me to get authenticated user
        User me = graphClient
            .me()
            .buildRequest()
            .get();

        return me;
    }
    
    public static List<Event> getEvents(String accessToken) {
        ensureGraphClient(accessToken);

        // Use QueryOption to specify the $orderby query parameter
        final List<Option> options = new LinkedList<Option>();
        // Sort results by createdDateTime, get newest first
        options.add(new QueryOption("orderby", "createdDateTime DESC"));

        // GET /me/events
        IEventCollectionPage eventPage = graphClient
            .me()
            .events()
            .buildRequest(options)
            .select("subject,organizer,start,end")
            .get();

        return eventPage.getCurrentPage();
    }
    
    public static  List<CalendarGroup> calendarGroup(String accessToken) {
    	 ensureGraphClient(accessToken);
    	ICalendarGroupCollectionPage calendarGroups = graphClient.me().calendarGroups()
    			.buildRequest()
    			.get();
    	return calendarGroups.getCurrentPage();
	}
}