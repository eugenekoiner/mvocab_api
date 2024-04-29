package opensubtitles;

import org.apache.xmlrpc.XmlRpcException;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, XmlRpcException {
        OpenSubtitle openSubtitle = new OpenSubtitle();
        openSubtitle.login();
        //openSubtitle.ServerInfo();
        //openSubtitle.getSubLanguages();
        //openSubtitle.getMovieSubsByName("now you see me","20","eng");
        SubtitleInfo url = openSubtitle.getTvSeriesSubs("Kidding", "1", "1", "10", "eng").get(2);

        //openSubtitle.Search("D:/Downloads/Minions.2015.720p.BRRip.850MB.MkvCage.mkv");
        openSubtitle.downloadSubtitle(new URL(url.getSubDownloadLink().replaceAll(".gz", "")), "D:\\Downloads\\" + url.getMovieName().replaceAll("\"", "'") + ".txt");
        openSubtitle.logOut();
    }
}

