/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of 
 * Dmitri Anatol'evich Pisarenko.
 * 
 * Copyright 2010, 2011 Dmitri Anatol'evich Pisarenko
 * All rights reserved
 *
 **/

package at.silverstrike.pcc.test.twoleggedoauth;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.PrivateKey;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.PccException;

import at.silverstrike.pcc.api.privatekeyreader.PrivateKeyReader;
import at.silverstrike.pcc.api.privatekeyreader.PrivateKeyReaderFactory;
import at.silverstrike.pcc.impl.privatekeyreader.DefaultPrivateKeyReaderFactory;

import com.google.api.client.auth.oauth.OAuthHmacSigner;
import com.google.api.client.auth.oauth.OAuthParameters;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.tasks.v1.Tasks;
import com.google.api.services.tasks.v1.Tasks.Tasklists.List;
import com.google.api.services.tasks.v1.model.TaskList;
import com.google.api.services.tasks.v1.model.TaskLists;
import com.google.gdata.client.GoogleService;
import com.google.gdata.client.authn.oauth.GoogleOAuthHelper;
import com.google.gdata.client.authn.oauth.GoogleOAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthHmacSha1Signer;
import com.google.gdata.client.authn.oauth.OAuthRsaSha1Signer;
import com.google.gdata.client.authn.oauth.OAuthParameters.OAuthType;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.BaseEntry;
import com.google.gdata.data.BaseFeed;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.Feed;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.calendar.CalendarFeed;
import com.google.gdata.data.extensions.When;
import com.google.gdata.util.ServiceException;

/**
 * @author DP118M
 * 
 */
public class Test2LeggedOAuth {
    private static final Logger LOGGER =
            LoggerFactory
                    .getLogger(Test2LeggedOAuth.class);

    @Test
    public void test2() {
        try {
            final com.google.gdata.client.authn.oauth.OAuthSigner signer =
                    new OAuthHmacSha1Signer();

            GoogleOAuthParameters oauthParameters = new GoogleOAuthParameters();

            String CONSUMER_KEY = "pcchq.com";
            String CONSUMER_SECRET = "6KqjOMZ90rc7j252rn1L9nG2";

            oauthParameters.setOAuthConsumerKey(CONSUMER_KEY);
            oauthParameters.setOAuthConsumerSecret(CONSUMER_SECRET);
            oauthParameters.setScope("https://www.google.com/calendar/feeds");

            final CalendarService calendarService =
                    new CalendarService("pcchq.com");

            calendarService
                    .setOAuthCredentials(oauthParameters, signer);

            new GoogleOAuthHelper(signer);

//            printCalendars(calendarService);
            insertEvent(calendarService);

        } catch (Exception exception) {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        }
    }

    private void printCalendars(CalendarService calendarService) throws IOException, ServiceException {
        // TODO Auto-generated method stub
        final URL feedUrl =
                new URL(
                        "https://www.google.com/calendar/feeds/default/owncalendars/full?xoauth_requestor_id=dmitri.pissarenko@gmail.com");
        final CalendarFeed resultFeed =
                calendarService.getFeed(feedUrl, CalendarFeed.class);

        LOGGER.debug("resultFeed: {}", resultFeed);

        LOGGER.debug("Your calendars:");

        CalendarEntry pccCalendar = null;
        for (int i = 0; (i < resultFeed.getEntries().size())
                && (pccCalendar == null); i++) {
            final CalendarEntry entry = resultFeed.getEntries().get(i);

            if ("PCC".equals(entry.getTitle().getPlainText())) {
                pccCalendar = entry;
            }
        }

    }

    private void insertEvent(final CalendarService calendarService)
            throws MalformedURLException, IOException, ServiceException {
        URL feedUrl =
                new URL("https://www.google.com/calendar/feeds"
                        + "/private/full?xoauth_requestor_id="
                        + "dmitri.pissarenko@gmail.com");

        LOGGER.debug("feedUrl: {}", feedUrl);
        LOGGER.debug("calendarService: {}", calendarService);

        // Prepare entry
        CalendarEventEntry entry = new CalendarEventEntry();

        entry.setTitle(new PlainTextConstruct("abc"));
        entry.setContent(new PlainTextConstruct("def"));

        DateTime start = new DateTime(new Date());
        DateTime end = new DateTime(new Date());
        When eventTimes = new When();
        eventTimes.setStartTime(start);
        eventTimes.setEndTime(end);
        entry.addTime(eventTimes);

        // Insert entry
        CalendarEventEntry insertedEntry =
                calendarService.insert(feedUrl, entry);
    }
    @Test
    public void test3()
    {
        try
        {
            // Initializing some Objects
            HttpTransport httpTransport = new NetHttpTransport();
            JacksonFactory jsonFactory = new JacksonFactory();

            // The 2-LO authorization section
            OAuthHmacSigner signer = new OAuthHmacSigner();
            final String CONSUMER_SECRET = "6KqjOMZ90rc7j252rn1L9nG2";
            signer.clientSharedSecret = CONSUMER_SECRET;

            OAuthParameters oauthParameters = new OAuthParameters();
            oauthParameters.version = "1";
            oauthParameters.consumerKey = "pcchq.com";
            oauthParameters.signer = signer;
            oauthParameters.signRequestsUsingAuthorizationHeader(httpTransport);

            // Initializing the Tasks API service
            Tasks service = new Tasks("2-lo-tasks-test/1.0", httpTransport, jsonFactory);
            String API_KEY_FROM_APIS_CONSOLE = "6KqjOMZ90rc7j252rn1L9nG2";
            service.accessKey = API_KEY_FROM_APIS_CONSOLE ;

            // Performing first request: Getting the tasks lists
            List getTaskListsOperation = service.tasklists.list();
            getTaskListsOperation.unknownFields.add("xoauth_requestor_id", "dmitri.pissarenko@gmail.com");
            TaskLists taskLists = getTaskListsOperation.execute();

            // Simply printing the title of each tasks lists
            for (TaskList taskList : taskLists.items) {
              System.out.println(taskList.title);
            }
            
        }
        catch (final Exception exception)
        {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        }
    }
}
