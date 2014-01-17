package steam_reddit_table;

import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ca.bsolomon.api.steam.SteamAPI;
import ca.bsolomon.api.steam.dao.AppDetails;
import ca.bsolomon.api.steam.formatter.RedditTableFormatter;

@ManagedBean(name="steamBean")
@ViewScoped
public class SteamBean {

	private String appIds;
	private String redditTable = "";
	
	public String getAppIds() {
		return appIds;
	}
	public void setAppIds(String appIds) {
		this.appIds = appIds;
	}
	public String getRedditTable() {
		return redditTable;
	}
	public void setRedditTable(String redditTable) {
		this.redditTable = redditTable;
	}
	
	public void generate() {
		List<String> appIdList;
		
		String[] data = appIds.split(",");
		for (int i=0;i<data.length;i++) {
			data[i] = data[i].trim();
		}
		
		if (data.length > 0) {
			appIdList = Arrays.asList(data);
			List<AppDetails> details = SteamAPI.getAppDetails(appIdList);
			redditTable = RedditTableFormatter.formatTable(details);
			redditTable = redditTable.replaceAll("\\n", "<\\br>");
		}
	}
}
