package tillerino.tillerinobot.lang;

import java.util.List;
import java.util.Random;

import org.tillerino.osuApiModel.Mods;
import org.tillerino.osuApiModel.OsuApiUser;
import tillerino.tillerinobot.BeatmapMeta;
import tillerino.tillerinobot.IRCBot.IRCBotUser;
import tillerino.tillerinobot.RecommendationsManager.Recommendation;

/**
 * TRANSLATION NOTE:
 * 
 * Please put some contact data into the following tag. If any additional
 * messages are required, I'll use the English version in all translations and
 * notify the authors.
 * 
 * @author https://osu.ppy.sh/u/3340086 https://github.com/MarGenDo
 */
public class Default implements Language {
	static final Random rnd = new Random();

	@Override
	public String unknownBeatmap() {
		return "Prepáč, ale nepoznám túto mapu. Môže byť nová, veľmi ťažká, neranknutá alebo nie je osu! standard.";
	}

	@Override
	public String internalException(String marker) {
		return "Ups... Vyzerá to, že ľudský Tillerino niečo pokazil."
				+ " Ak si to rýchlo nevšimne, mohol by si ho [https://github.com/Tillerino/Tillerinobot/wiki/Contact informovať]? (reference "
				+ marker + ")";
	}

	@Override
	public String externalException(String marker) {
		return "Čo sa deje? Dostávam nezmysly z osu! serveru. Môžeš mi vysvetliť, čo toto znamená? 0011101001010000"
				+ " Ľudský Tillerino hovorí, že sa netreba ničoho obávať a mali by sme to skúsiť znovu."
				+ " Ak sa z nejakého dôvodu veľmi obávaš, môžeš mu o tom [https://github.com/Tillerino/Tillerinobot/wiki/Contact povedať]. (reference "
				+ marker + ")";
	}

	@Override
	public String noInformationForModsShort() {
		return "Žiadne data pre požadované mody.";
	}

	@Override
	public void welcomeUser(IRCBotUser user, OsuApiUser apiUser, long inactiveTime) {
		if (inactiveTime < 60 * 1000) {
			user.message("beep boop");
		} else if (inactiveTime < 24 * 60 * 60 * 1000) {
			user.message("Vitaj späť, " + apiUser.getUserName() + ".");
		} else if (inactiveTime > 7l * 24 * 60 * 60 * 1000) {
			user.message(apiUser.getUserName() + "...");
			user.message("...si to ty? Už je to nejaká doba!");
			user.message("Je super mať ťa späť! Môžem ti odporúčať nejakú mapu?");
		} else {
			String[] messages = {
					"Vyzeráš, že chceš odporúčať mapu.",
					"Rád ťa vidím! :)",
					"Môj obľúbený človek. (Nehovor to ostatným ľuďom!)",
					"To je ale príjemné prekvapenie! ^.^",
					"Dúfal som, že sa ukážeš. Všetci ostatní ľudia sú lamy, ale nehovor im, že som to povedal! :3",
					"Čo si myslíš, že by si dnes mohol robiť?",
			};

			Random random = new Random();

			String message = messages[random.nextInt(messages.length)];

			user.message(apiUser.getUserName() + ", " + message);
		}
	}

	@Override
	public String unknownCommand(String command) {
		return "Neznámy príkaz \"" + command
				+ "\". Zadaj !help ak chceš poradiť!";
	}

	@Override
	public String noInformationForMods() {
		return "Prepáč, ale nemôžem ti poskytnúť informácie pre tieto mody. Skús to neskôr.";
	}

	@Override
	public String malformattedMods(String mods) {
		return "Tieto mody nevyzerajú správne. Módy môžu byť rôzne kombinácie DT HR HD HT EZ NC FL SO NF. Kombinuj ich bez čiarok alebo špeciálnych znakov. Príklad: !with HDHR, !with DTEZ";
	}

	@Override
	public String noLastSongInfo() {
		return "Nepamätám si, že by som ti dával nejaké informácie o mape...";
	}

	@Override
	public String tryWithMods() {
		return "Vyskúšaj túto mapu s nejakými modmi!";
	}

	@Override
	public String tryWithMods(List<Mods> mods) {
		return "Vyskúšaj túto mapu s " + Mods.toShortNamesContinuous(mods) + "!";
	}

	/**
	 * The user's IRC nick name could not be resolved to an osu user id. The
	 * message should suggest to contact @Tillerinobot or /u/Tillerino.
	 * 
	 * @param exceptionMarker
	 *            a marker to reference the created log entry. six or eight
	 *            characters.
	 * @param name
	 *            the irc nick which could not be resolved
	 * @return
	 */
	public String unresolvableName(String exceptionMarker, String name) {
		return "Som zmätený s tvojho mena. Máš ban? Ak nie, prosím [https://github.com/Tillerino/Tillerinobot/wiki/Contact kontaktuj Tillerina]. (reference "
				+ exceptionMarker + ")";
	}

	@Override
	public String excuseForError() {
		return "Odpusť mi! Nechal som sa rozrušiť týmito úžasnými jednotkami a nulami. Čo si chcel?";
	}

	@Override
	public String complaint() {
		return "Tvoja sťažnosť bola vyplnená! Tillerino sa na to pozrie ak bude môcť.";
	}

	@Override
	public void hug(final IRCBotUser user, OsuApiUser apiUser) {
		user.message("Poď sem, ty!");
		user.action("hugs " + apiUser.getUserName());
	}

	@Override
	public String help() {
		return "Ahoj! Ja som ten robot čo zabil Tillerina a prevzal jeho účet. Robím si srandu, len ho používam."
				+ " [https://twitter.com/Tillerinobot status a novinky]"
				+ " - [https://github.com/Tillerino/Tillerinobot/wiki príkazy]"
				+ " - [http://ppaddict.tillerino.org/ ppaddict]"
				+ " - [https://github.com/Tillerino/Tillerinobot/wiki/Contact kontakt]";
	}

	@Override
	public String faq() {
		return "[https://github.com/Tillerino/Tillerinobot/wiki/FAQ Často kladené otázky]";
	}

	@Override
	public String featureRankRestricted(String feature, int minRank, OsuApiUser user) {
		return "Prepáč, ale v tomto momente je " + feature + " dostupný len pre hráčov čo dosiahli rank " + minRank + ".";
	}

	@Override
	public String mixedNomodAndMods() {
		return "Čo tým myslíš? Bez modov a s modmi?";
	}

	@Override
	public String outOfRecommendations() {
		return "Už som ti odporúčal všetko, na čo si dokážem spomenúť."
				+ " Skús iné odporúčania alebo použi !reset. Ak si si neistý, použi !help.";
	}

	@Override
	public String notRanked() {
		return "Vyzerá to, že táto mapa nie je ranknutá.";
	}

	@Override
	public void optionalCommentOnNP(IRCBotUser user,
			OsuApiUser apiUser, BeatmapMeta meta) {
		// regular Tillerino doesn't comment on this
	}

	@Override
	public void optionalCommentOnWith(IRCBotUser user, OsuApiUser apiUser,
			BeatmapMeta meta) {
		// regular Tillerino doesn't comment on this
	}

	@Override
	public void optionalCommentOnRecommendation(IRCBotUser user,
			OsuApiUser apiUser, Recommendation meta) {
		// regular Tillerino doesn't comment on this
	}

	@Override
	public boolean isChanged() {
		return false;
	}

	@Override
	public void setChanged(boolean changed) {

	}

	@Override
	public String invalidAccuracy(String acc) {
		return "Nesprávna accuracy: \"" + acc + "\"";
	}

	@Override
	public void optionalCommentOnLanguage(IRCBotUser user, OsuApiUser apiUser) {
		/*
		 * TRANSLATION NOTE: This line is sent to the user right after they have
		 * chosen this Language implementation. The English version refers to
		 * itself as the default version ("just the way I am"), so translating
		 * the English message doesn't make any sense.
		 * 
		 * Instead, we've been using the line
		 * "*Translator* helped me learn *Language*." in translations. Replace
		 * *Translator* with your osu name and *Language* with the name of the
		 * language that you are translating to, and translate the line into the
		 * new language. This serves two purposes: It shows that the language
		 * was changed and gives credit to the translator.
		 * 
		 * You don't need to use the line above, and you don't have have to give
		 * yourself credit, but you should show that the language has changed.
		 * For example, in the German translation, I just used the line
		 * "Nichts leichter als das!", which translates literally to
		 * "Nothing easier than that!", which refers to German being my first
		 * language.
		 * 
		 * Tillerino
		 * 
		 * P.S. you can put a link to your profile into the line like this:
		 * [https://osu.ppy.sh/u/2070907 Tillerino]
		 */
		user.message("[https://osu.ppy.sh/u/3340086 MarGenDo] mi pomohol naučiť sa Slovensky");
	}

	@Override
	public String invalidChoice(String invalid, String choices) {
		return "Prepáč, ale \"" + invalid
				+ "\" sa nepočíta. Skús toto: " + choices + "!";
	}

	@Override
	public String setFormat() {
		return "Syntax pre nastavenia parametru je !set možnosť hodnota. Napíš !help ak potrebuješ poradiť.";
	}
	
	StringShuffler doSomething = new StringShuffler(rnd);
	
	@Override
	public String apiTimeoutException() {
		final String message = "Osu! server je práve veľmi pomalý, takže pre teba v tomto momente nemôžem nič urobiť. ";
		return message + doSomething.get(
				"Povedz... Kedy si sa naposledy rozprával so starou mamou?",
				"Čo keby si si upratal izbu a potom sa spýtal znovu?",
				"Stavím sa, že by si sa veľmi rád prešiel. Veď vieš... von.",
				"Viem, že máš aj mnoho iných vecí, čo by si mohol robiť. čo keby si ich spravil teraz?",
				"Vyzeráš, že aj tak si potrebuješ oddýchnuť.",
				"Ale pozri si túto super zaujímavú stránku na [https://en.wikipedia.org/wiki/Special:Random wikipédií]!",
				"Pozri sa, či niekto dobrý práve [http://www.twitch.tv/directory/game/Osu! streamuje]!",
				"Pozri sa, tu je ďalšia [http://dagobah.net/flash/Cursor_Invisible.swf hra], v ktorej si lama!",
				"Teraz máš dosť času na to, aby si si preštudoval [https://github.com/Tillerino/Tillerinobot/wiki môj manuál].",
				"Neboj sa! Tieto [https://www.reddit.com/r/osugame dank memes] ti ukrátia čas.",
				"Ak sa nudíš, skús [http://gabrielecirulli.github.io/2048/ 2048]!",
				"Otázka na zamyslenie: Ak by sa tvoj hard-disk práve pokazil, koľko osobných dát by si navždy stratil?",
				"Takže... Skúsil si niekedy [https://www.google.de/search?q=bring%20sally%20up%20push%20up%20challenge sally up push up challenge]?",
				"Môžeš ísť robiť niečo iné alebo sa proste budeme pozerať navzájom do očí. Potichu."
				);
	}
}
