package test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class WipeListedUsers {

	private static final List<String> UNMODIFIED_CONTENT = Arrays.asList("[ACCOUNT]", "[CHARACTER]", "[EQUIPMENT]", "[LOOK]", "[SKILLS]", "[ITEMS]", "[BANK]", "[FRIENDS]",
			"[HOLIDAY-EVENTS]", "[DEGRADEABLES]", "[ACHIEVEMENTS-TIER-1]", "[ACHIEVEMENTS-TIER-2]", "[ACHIEVEMENTS-TIER-3]", "[IGNORES]", "[PRESETS]", "[KILLSTREAKS]", "[TITLES]",
			"[NPC-TRACKER]", "[EOF]",

	"character-username", "character-password", "character-rights", "revert-option", "mode", "tutorial-stage", "play-time", "character-hp", "character-posx", "character-posy",
			"bank-pin", "bank-pin-cancellation", "bank-pin-unlock-delay", "bank-pin-cancellation-delay", "connected-from", "character-skill", "startPack");

	private static final Collection<String> USERS = Arrays.asList("haidere", "erinbullysme", "swords drawn", "gfh6fd45g", "onlyy", "king jig", "boss hoss", "aieron", "slimey aj",
			"the wirral", "spontaneous", "shurt bus", "dishwasher75", "satan", "crispyyy", "py1112", "minutzu", "pickle man56", "pickle man55", "wngdymlviosa", "fabi owns",
			"0llie", "toxic legend", "99whipping", "crusades", "ace v3", "iron paint", "ace v2", "nameka", "dr dubsteq", "rag n stake", "no way o u t", "osama stfu",
			"i likr fishy", "survuvin", "ospoopahar", "jesljoe334", "123123123sdf", "skill4step", "tickle", "the overmule", "samu", "thecrash", "mw1234", "phatshock",
			"iron polish", "arunforce", "l00ter nub7", "lilililiil", "0nlgger0", "leffens dad", "ogg ogg ogg", "flip", "xoxoxoxoxox", "hybrds jr", "nigga lynch", "permed 4life",
			"levinator", "siege legend", "g", "mooberate", "king mala", "jannabear", "lord velion", "scape a thot", "ext skiller", "mud hut 1", "squaaaw", "yk basic", "a r m y",
			"sanpix", "fuck mods", "wrap", "adopted", "aasfdsaf", "violent", "shazammwow", "big tits1", "new york boy", "erin eat cum", "sga123", "brids r fags", "bangbanglol",
			"business13", "its boy", "dopie", "iron smited", "staff chanre", "whats a gp", "flux risk", "nickers", "i mahatma 1", "jack black", "xrunepure", "12345678fgi",
			"ogveldahar", "nigatron", "lel3", "o b a m a", "gooonsquad", "iron matt", "ecotic c", "f a4 i lz do", "cya m8", "evl pk3r", "yawpton", "0 m a m a", "stone squad",
			"mr mucho lad", "donttell", "cryptologic", "fluffy", "lee sin moit", "1337pwner", "perfectas", "pluxy", "dbattleaxe", "xarni", "gl 16 hours", "fam3e", "iron chase",
			"lelll", "iron kj", "omni acid", "onux", "pretyingname", "mr rich", "blue popo", "a black kid", "filthy snach", "i like fishing", "pluxyzanarit", "da weeknd",
			"im logicz", "siege legion", "potupson", "koga", "perm mute", "mixtures", "diazz", "toxicdeftank", "kgdskfkdsfkd", "welf4re pk", "ser fatneck", "gee wiz", "salty dike",
			"old fags", "kilogram", "tariq", "vesta shlong", "duping al", "rkid", "loeba", "suck my dick", "lets talk", "dancing with", "kryptonite", "killa4life", "mass murder",
			"my kdrs", "black 5tone", "sellingporno", "comp goals", "taylorxswift", "46xp skiller", "roonscape", "leffens dong", "pker42", "0      i", "ill1llllllll", "legendarys",
			"qcfilqc", "kirky", "treyr0", "mdma", "instaeatpoop", "shorts", "king nolac", "king matt", "misclick irl", "i  i  l ii", "fight night", "logany5", "nr 1 pure",
			"kingduffy1", "ttbudzbudz", "13 gp", "crispian", "i is yournan", "wishme", "il1l1ll3ll3l", "usain bolt", "stefanuchis", "aof rang", "zz1234", "cudiaz", "jhsdjgfsad78",
			"therscaper", "nigga wizard", "300 4 demote", "mwm        8", "mwm        7", "mwm        6", "h e r p", "i         i", "0        i", "namuhn1z", "project dec",
			"legendary2", "ul jason", "nlgger", "killingnewbs", "cpl", "notpickleman", "hungry jacks", "skream", "matty ice", "jimmy hendri", "yamaha skill", "ellery4demot",
			"shining", "qweqweqweqwe", "yesmee", "daveed", "rekloosif", "voli", "dkdjdkdjk", "stfufaggots", "obby maul k0", "ironrandy fk", "treborv", "gucci mane", "overdose2940",
			"legacy217", "am atm c", "tth", "ant7567", "50 cal", "boltonline", "99ranged99", "legacy218", "silentpure", "3inch", "muckazz", "suomii", "runecustom", "coco ownz u",
			"bubbas pure", "valintine k0", "ily mr italy", "mastercard", "y   o l o o0", "staff chanfe", "supply", "underdosed", "fk this serv", "rick grimes", "sealteamsix",
			"classic kill", "git rekt fag", "potter12", "cth", "vsux", "magic pure1", "lew", "makeitstack", "pumpkin pie", "nyjah", "l0l127638128", "pop91935", "torie",
			"walfredito", "ilililiiil", "c u hick", "lewy", "staff change", "holyfalkerz", "mind of fink", "jordzeh", "unbannablee", "user37419", "names andy", "li hax il",
			"veracs prod", "wiggywoo642", "texown", "raped a mod", "logicz", "accident", "krak ten", "baby sloth", "david is gay", "xp idope", "spartan m1lf", "xxshadow90",
			"y         6", "empathy", "yak", "kingskane", "pvm4dayz", "alpha chi", "clive", "luckychance5", "giovanni", "koon", "pvm legend", "weslay2day", "the sheriff", "downsx",
			"dont rag3", "user515555", "medical", "me6ua", "b0ssin", "legitness", "shmurda jr", "sam xoxo", "detergent", "daltino", "u fap 2 cp", "death alls", "elery",
			"iron obama", "last", "il jason", "tiki", "o t f", "o0o0oooo000o", "i dont off", "doubt", "instaeatdick", "papa xanax", "cow cow cow", "pawnups", "proximity",
			"mod gert", "asdjklhasd", "oh tentac;e", "google007", "do your job", "dope dealerr", "gupup", "il0vesanta", "lord malo", "na panela", "sighhh", "albinoo",
			"twintowergon", "lutter", "bellaah", "cronus", "aspects", "rev rift", "loskatnaama", "hardwell", "rs acc", "ez win", "exposed ayy", "frutex", "you neeq", "niggerboy51",
			"jimmeh", "arena 1", "pure nerd", "ab soul", "unmissable", "alkoh0l", "blazzerd", "jacoblund", "do yoru job", "toolazy2move", "shitty", "ipkmills", "hide account",
			"baltoy", "snoop lion", "contradictio", "mr barrows", "haitian", "albinos", "midz", "iron wundo", "pure essence", "happy", "green gold", "dgfdgfdgfsdg", "whizy v2",
			"bruce wayne", "r7o", "iiii ii i  l", "redjukas", "aspkikr", "party hat", "partyhat", "authorized", "flyfly", "slabbey", "rag init", "phishing", "yk man", "karma v2",
			"jb in iron", "i hanter i", "kitty meow", "runelocus", "last man dan", "saumowns", "knicks", "2jimmy1", "pure m e r k", "infection", "im the pure", "a7x", "gold boy",
			"numberpker1", "purehis", "elite", "hahahaililol", "gimli", "sinxp", "cum 4 ellery", "ohsol", "bravey2", "mods are gay", "nacho limey", "patience fri", "survive",
			"hound", "ill1llll1ll2", "god of duel", "walfredino", "jordzehh", "shityu", "foster", "sinkdawg", "runecustom69", "900s", "pr4y3r b3st8", "fish fetish", "speedy",
			"regular guy", "kamakawiwo", "oranges uk", "os owner", "spectacula", "slimshady", "rsn mobster", "boxxy", "thacrypt", "hardestgang", "darker mans", "i m nice die",
			"niggah killa", "rollout", "cosmic rush", "nh danny", "para dice", "dharoks bruh", "dong", "maldesto", "retarded fuc", "i zerk it", "i p v p i", "kalupe", "gg scrub",
			"killer2h", "right shoe", "eliot", "polished", "pkerjosh", "iron lel", "zanarithbett", "dark u suck", "mr po po", "ellery123", "iron toaster", "erinist", "holditdown",
			"lumix v234", "maunda", "atu ni", "lucifer", "nanpoop984", "troll 3", "dope", "lunchbox", "i am versace", "cum        q", "n p stoner", "elleryisfaggo", "iambob",
			"klow46", "staff chaewr", "earl", "cooper", "so damn rich", "w301 edge", "fiery", "piolho", "loopy4ul0l0l", "reksai", "declinedeath", "trani", "kaotix", "alcheny",
			"ragaroo", "based jesus", "joxxi", "certified og", "nigger lynch", "sarizen", "leffens mum", "santa h o t", "zone six", "theo fag", "black sheep", "erinisabully",
			"luidzispkz", "mild", "ashes", "erinbully420", "bill", "erinistheo", "never try", "mili", "iron thumbs", "future dream", "cryptologic1", "l2skill", "korean smurf",
			"senka", "hey its me1", "bait artist", "runixscapev7", "stake bro", "indo", "level 3i", "rambi bambi", "li lwif leig", "taylorrr", "ags smdunmtu", "tekken", "exilor2",
			"gp mule", "blitzzx", "moobage", "im a faggot", "im bonkers", "philadelphia", "sweatshirt", "i mahatma 15", "my cockkkkkk", "missable", "lemonnn", "brad", "maze 4 ban",
			"loot 4 me", "jonesyxuk", "cold shot", "business", "gucci bowtie", "prentz", "mucka", "ep1k", "staff", "estrorhu", "littlekiller", "slay yo bch", "setup abuses",
			"123123213", "deathmachine", "niggerkiller", "fishingcouch", "peckishwhale", "xplicit maul", "i bos u", "d e m o ns3", "i am ab", "scotland", "a        l",
			"holecancer", "say hl", "z 3 r k", "some12", "skelly", "xxshadowads", "vzexal", "ikillmuslims", "the next", "creep", "eveee", "scoutup", "resale", "dustinsisiron",
			"hse", "y          8", "mitch pker", "dkf", "ragger101", "wortho", "coach seb", "hesaidbraap", "bronze dildo", "kidma", "clit licker", "tgodwizz", "pixel", "hayden 15",
			"red wings", "kowah", "pokemon", "madison", "skellyy", "stefanuchiss", "dustscapeorg", "da pk pro", "killed", "y          e", "multilogging", "fredosantana", "tame",
			"eazy", "gl me u die", "lucario3", "reptiliansss", "atrocious", "pateex", "sn0wballs", "crunchy fish", "swish skills", "netflix porn", "muramasa", "o          o",
			"mirage", "masterb8", "illlillllll0", "qweqweqwe", "pkol   ol", "my cockkkkk", "random name", "grams", "elinkos", "r3kts0n", "nih ger", "i tank kids", "jamie boy",
			"wickymoon", "ags to dds", "o          i", "loading", "menace", "tribalv2", "rooscape", "blazze", "o     i", "iron joshh", "g o 0 n", "o     o", "skiller j", "hh54h",
			"abnant", "sarizen752", "x t dizzle x", "slap kids", "lovely girls", "frutexss", "dox", "loeb", "fallingpaulk", "kalphiteking", "illlillllllp", "nan lift", "zalupa",
			"shoono", "dont try", "ultimane", "koitas ny", "may", "hi im wayne", "cannaerts5", "meth is good", "legendarys1", "donutz", "reptilians", "300", "paki v2", "rusko",
			"soz buttfked", "maxxaroth", "spiritdash", "d c l a w", "0         i", "pawnup", "hosl", "ghhh", "dre", "xxwizeword", "sorrow angel", "mewtid", "mr steal", "host",
			"dro", "nem evol i", "death rune34", "boss product", "im fucking g", "im fucking j", "gl survuvin", "heart", "briz", "learn 2 boss", "true", "madlad", "ebay",
			"alts alt", "omfg69", "dontfishme", "lumix v2", "niggers die", "smexy swag", "quantum", "hxgs", "runite ore", "john stoner", "tommyburr", "o          8", "fseffwfw",
			"h u s s l e", "physical", "leffens mom", "ghrehrtrh", "mrs leffen", "heatonz", "juanito boy", "gods child", "fuck muhamed", "jrexey 22", "ashten", "oranges uks",
			"fuckboy", "shaher25", "urafgt", "huge titties", "durvuvin", "el loves dic", "alkosor", "kill mez", "sdfhdth", "2noobs1gp", "ironism", "wow srs", "vegimite",
			"haitian hype", "i am lucky", "kothegod", "balzaqmanjaq", "qcrelixqc", "r0t323923230", "loskatnaamal", "porn star", "lopitols", "c c tm", "loyalone", "fire",
			"whats nh", "bank loota", "13", "liyky2", "cya m8888888", "cum drizzle", "willows", "asd564fs6a5d", "hxgz", "mickymaxcash", "black boner", "watermelonv2", "joxii",
			"cassie ltfu", "the plug", "the flash", "21", "0x", "peruna", "roonskape", "mid", "bananas", "mikey os", "iron duffy", "arabian", "jister", "min", "eb0la",
			"crunchy frog", "fzaezaf", "darenas", "galaxy", "spankied", "xperia", "mano", "mynameis3333", "mr icey", "why so mad", "saint entity", "ii brid ii 604800", "1v1",
			"dodleree", "simon", "conjuring", "elpollo18", "daryl dixon", "mani", "iron bitch", "99cents", "pr4y3r b3st", "ililililjf", "pk kill123", "imbackagain", "ellery4dem",
			"mori jr", "scape school", "sick databas", "schlif", "belly button", "lilililioil0", "u die i lol", "ebola vv", "jo3y", "darkey", "i storage i", "2007 pk",
			"jew inbound", "i  i     i o", "darren111", "i  i     i l", "iownlolf", "fu iam owner", "maximum8", "newman", "o      i", "spookyman", "pikajew", "skillercouch",
			"rockin lemon", "shark fetish", "blm pure", "big ass dick", "legacy 6144", "itsame mario", "o b a", "zulrahs pure", "spink", "pink pussy", "shark attack",
			"bodybuilder", "thebluedude1", "iron farmer", "ez clear", "holyhalker", "scape sos", "sillym0do", "red bull", "just a shaco", "raped", "penis muncha", "step",
			"maximum10", "gooseman", "such as ko", "loebas", "mog", "king nolack", "invidia isdk", "small jacket", "ajmcbsat", "loopy4u", "bear head", "came 2 pk", "xatu",
			"kaatumis alt", "rich boi", "vinaldo", "90", "mox", "deathby1812", "obstacle", "iron mikey", "only stack", "omni shieldz", "iron reach", "landlubber", "dragon fart",
			"grimly", "siegel legen", "makmister", "my name isw", "velvet purse", "im zerk off", "am atm", "pentium", "finding nemo", "jesusonadino", "doped up", "ghst",
			"kaatumis", "0 s a m a", "poiz", "muted 60 min", "il rush pure", "unprosperoui", "rumadornah", "googly eye12", "classical", "oh tentacle", "cadesonnn", "pk friendly",
			"black nigger", "ash x d", "skye", "kyle", "dont try m", "h4mm", "vanitypk", "weslay2da", "99 mage hbu", "getting bank", "tomii", "picklesucker", "skiller juan",
			"iron whido", "mule6969", "oso skilled", "mrs", "bandos1899", "warrior49233", "willows xd", "mvp jake", "pizzahut", "zamorak pk", "missablel", "abnants bae",
			"rockshift", "niggggggaaa", "pure lad", "jason jr", "132a1gsafgag", "safepk", "maze", "noxpwaste", "ab likes guy", "ff doesnt pk", "mod slayer", "html", "htydjtdhdt",
			"vg2", "jord obby", "leffens nan", "spade", "gmax", "wild bird", "shitcomunity", "hill dogs", "big fat dick", "shithybrid15", "anti defence", "ace first 1",
			"i   i   l li", "dkdjdkdjkd", "xdxdxdxdxd", "libby", "o         p", "just a brad", "muk", "o         i", "free shurt", "instaeatopoop", "elder", "network",
			"lllllllillll", "meet beek", "robert moles", "trace", "miika1", "i dontglitch", "blackbad", "l u x", "girlpower88d", "degrade", "siika hat", "fewb", "am atm caleb",
			"stephen", "verified", "crisdafierc2", "god blesz22", "mvx", "command", "killer521", "mrkuush", "allie", "weedonlip", "c u", "nuffinpuffin", "bboywilliams",
			"foot fetish", "death awaits", "rektcity", "egg", "markoskiller", "ospvpblows", "my name is b", "funkybitch82", "ddos    l", "masteredpvp", "snapie", "washmysog18",
			"jizzonu", "raped nigger", "washmydog18", "mod sight", "os anchor", "theforgotten", "leggoooo", "loony", "funkey", "pickle manv2", "cock    o  i", "lcbs", "placement",
			"dont gas", "amazon mess", "xp idopee", "roflscape", "skill zone", "deeznutsss", "konkerz", "iqr", "dollar", "ogkush", "help mez", "antwan", "gl 24 hours",
			"tylonsmash", "raizen", "jokah", "abhinav104", "jrexeys v5", "wangs", "mikehunt69", "werqerwerw", "kobebeanie", "logical", "wanker", "team sync", "0wn life2", "acex",
			"paypah maker", "broken arm", "lynch nigger", "owner dave", "borboar", "gibs", "jadenkush", "i zerk off", "dopekillin", "jungleorafk", "l6skill", "y        l",
			"jhonnoss", "white sheep", "keep you up", "bearhead", "copycat", "i rag i", "visahfx", "matts slave", "never trys", "purepkeri", "buydeviouspk", "corey king",
			"i  i       i", "queen brid", "duffel bag", "ilikecookies", "5348584", "acid boys", "nullify", "instaiscrap", "itz", "skill 4 dayz", "vp1", "bigbootyjudy", "f34r xero",
			"bakkes2", "illuminati", "pickwick ice", "king badbrid", "united", "easy name", "hi im cool", "oxycontin", "balls nigegr", "codomin", "modern times", "lililiii   i",
			"spreis", "077808878999", "erinfuknsux", "shurt busss", "fiji water22", "ops smited u", "werwerweqrr", "dat john", "iron logic", "milllllllllz", "doekoe", "sweat",
			"zxy", "heeeeeeeeeee", "feasting", "backup", "dozza", "promuff", "skt 1 faker", "iron darts", "namuhn1zl0l", "rii", "rik", "proximity0", "kayos", "lee sin gt",
			"king zerks1", "darkness", "gg idiot", "filftyil", "cragfitz", "legalize thc", "lubetube", "king zerk", "trybelogic", "lveinator", "waser39", "miley", "elpollo",
			"valesik", "iron vex", "d0ntbemad", "topple56", "sarizeng", "fsefloifs", "fu jong farm", "im currently", "tito506", "casey xoxo", "tom cruise", "iron jason", "syco",
			"o        i", "iron josh", "summonable", "elleryisdick", "dark is stup", "gayshemale", "oh tentalle", "s0solid", "sociopath", "mamba", "aki", "pm4ddos", "reefer",
			"faggotsstfu", "diviners", "fat wrecked", "nef", "teots", "onetwothrees", "leffens momy", "dfsasdf", "k0 k0 k0", "mean machine", "pker420", "sarizen1", "c c t m",
			"oh hi der", "johnarmstron", "x ownage x", "versace", "grim the rea", "vuup", "plastic fag", "qqqquuuavvvo", "7ofdiamonds", "crunck crunch", "waz473", "born2pk",
			"trick", "kitty", "matt is a", "illl1llll1ll", "ghetto ninja", "hide on bush", "neos", "lewis", "utykuykyukuy", "skepticism", "madgamer", "shurtbus", "0            i",
			"superbowl", "wafflehead", "golem", "tuna fetish", "spux", "bank of alb", "ninth", "thezerkerlad", "evl pk3r8", "1 hp pure", "nigger h8er", "nh or nh", "nero",
			"trash server", "adidas", "pron star", "bakkes", "grinderscape", "illili", "codar", "vileimage123", "catfish", "iron why ban", "your momma", "nigga slave5", "ape",
			"nigga slave6", "ilililili", "mithril drag", "rogie", "bobbyshurmda", "kingdufie12", "hostt", "1800bj", "os nate", "mod zezima", "c cash", "nigger h8erl", "u guys suk",
			"ducc squad", "lek48", "luretaylor", "hphphphphphphh", "kings spotty", "velstadt", "kendal2ez", "dice hulk v2", "whizy", "ddos", "gammawrecked", "wet feet",
			"ihateblack", "comeback", "get wr3ked", "kidm", "9 11 hahaani", "watermelon", "sit son", "exilor", "vanguardasdf", "yk bas", "riot", "zack effron", "tetsuen", "darky",
			"mod breach", "classic", "shit", "sit boe", "pkallday", "mith drag", "dat ass are", "from wckd", "synx", "siler", "o           i", "i rhmm", "blmv2", "god of pvp",
			"huge cock", "ashwaaa", "kiss me 100", "uxestrio", "slut", "demondrugss", "queer   illl", "d e m o ns", "faggot 4n", "raped faggot", "leaking", "ironmane", "iphone 07",
			"elskuz", "riskyy", "namekian", "i   i  l ii", "we be blazed", "i1l1l1l1ll1l", "moses og", "syn nyk", "banana peel", "o       o", "jrexey", "ext pvm king", "name",
			"diazz v2", "ili1i1oli1l", "jance", "diazz v5", "strongpk69", "bolt", "diazz v7", "perm rag420", "fuck dark", "crocodile", "il         i", "bullseye92", "i want blowy",
			"analpest", "hitlernigger", "king dark", "heretic", "placements", "dustinisback", "i spec hard", "almight f n", "placementz", "og volk", "king jiggg", "kwe3f",
			"colinboi", "i pk jews", "ellery 2dsa", "c u brid", "wal", "a rich nigga", "lililii", "deetrox", "cubanisb", "t dog 1995", "michael kors", "lizard pke", "yefefe",
			"ican", "the j0ker", "cumstainz4uz", "markos", "wyvern", "nulledcya", "zeronlovesy", "jigglet", "samanthaa", "lemon989", "sadcamel", "saradomin pk", "excruciate",
			"ans11", "loooooooooll", "raglasflkush", "slice", "penislikker6", "chillingscap", "osrs zulrah", "x bacon", "pizza feet37", "mr taylor xd", "nicepker", "remy", "omfg",
			"jrexey omg", "yankeediesel", "iiliilliii", "im not stone", "gl 15 hours", "chargebackz", "imone", "slayed847", "claasic", "armada", "jajaaj", "denial", "belladonna",
			"lord sasuke", "blvck", "illuminati42", "su ema", "maxmain", "durial1231", "wow wtf rlly", "so swavey", "ia m ab", "slayer5305", "turtleneck43", "kings ma", "zezima",
			"child of god", "trapp godd", "mod god", "i use f keys", "queer   ill1", "queer   ill8", "dice 55x2", "herpes 4 all", "i got balls", "i      i", "tacos4lyfe11",
			"fuck muhame", "420lsh pk", "franguinho", "drooz6", "niggatron", "f3r8hq33bqh8", "catfishes", "heres ebola", "wantod", "fu jong fish", "pking galore", "onika v2",
			"xyrox1", "tyler420", "savagefrmdao", "mules mule", "a n3w tank", "tryrt", "survival", "humidify", "mexicutioner", "kys matt", "ser matt", "ramela", "cumstainz4u",
			"kadabra", "suicidal", "namuhn1zl0la", "the exciled", "ms mal", "richest kid", "who", "36isakilo", "the wrath", "staf c", "dark is gay", "reptilian", "dat one kid1",
			"fungus", "tatsumi", "i dont talk", "ags to dds1", "phat shock", "not bugged", "phat shoch", "cum sauce", "iron harry", "stompax", "sav", "paprd1", "pvm theo", "p250",
			"zachs pure", "molestedtit", "swagmaster98", "dragonboy69", "soz im nigge", "peniscancer", "fargo", "900", "cracken123", "user", "player4666", "ditiskkririon", "wukey",
			"iown lodfds", "iron diaz", "synnn", "snoop dog", "f1rst h1t", "forge matt", "augs", "brid day", "camilaa", "chosen poo", "cunt flaps", "joe the noob", "oklahoma",
			"swe skiller", "im a 2ner", "jrg", "tombstonetim", "dice fox", "cosmin", "pvpure", "deadkid", "pizza442", "themjordans", "not true", "brother", "hurt", "big ass nuts",
			"frank", "ipkroobsi6", "niggerz", "bh", "max voider", "skill gains", "6ofdiamonds", "imisisallahh", "foolin", "jizz       l", "jizz       i", "0nly", "jizz       p",
			"luckscape", "founder", "leffen", "wish me luck", "swag123", "ilikecake", "shit mod", "kidsmash", "iron mikeyj", "xxwizewordxx", "420ish pk", "fuck u scape",
			"goldy man", "snoop dogg", "5lap kids", "bamf hayden", "iron basic", "fml", "sparticus", "anciiient", "rudey", "mr t", "moist gooch", "nexmaojen", "luke",
			"pro pker159", "clouds o", "aytz", "oxseraid", "a          l", "ravlen", "rayburn king", "ui jason", "conor fat", "manopk", "mc phat", "bh ", "fellowearth", "s0solid1",
			"rylie", "sgs", "mohim", "damas2", "i hate fags", "ewrerere", "nazzi", "jlilillillij", "phantom", "hbkenith", "blood og", "l     l  i", "senpai", "english king",
			"unknowns", "kurdo", "dawnfire", "iron suga", "basedjesus", "iron random", "amazonia", "legendary", "troll3d v2", "chronic baws", "bigboytheo", "class", "dice6",
			"p3skywasp22", "fu jong hai", "46xp", "herblorcouch", "wckd bank", "syndakid15", "fishing plez", "leffens cock", "leffens dady", "leffens cat", "silverisaac1",
			"goon26794", "drugs v2", "highrisk", "fiji water", "und1sput3d", "estrothu", "minhas", "demolished", "malafia", "aaaaaaaaaa", "gate", "owner jason", "mini nick",
			"i be who", "server owner", "im sean xd", "t4tt4", "bio", "niggerk1ller", "fuckinsta", "kaptain", "pridestalker", "liquidised", "brah brah7", "naami", "black lotion",
			"killer v4", "perf3ct life", "bronze v", "drag", "sley yo mage", "3xtra pk3r", "manipulation", "iron mold", "uncle ruckus", "dope deals", "iq", "dino", "distraction",
			"famous ftw", "xiben", "me6usa fam", "i am king", "pet dog", "poopy is col", "bailey jay", "bossing", "sam y7huekoc", "konys a bamf", "1nhuman", "maks gems",
			"legendary21", "peopledie", "ranged v2", "apple2mypie", "luke xd", "omnicide", "schliff", "my bitch", "italy", "notur mother", "dexteria", "lafini", "mickdog", "blm",
			"texskiller", "mr lolz", "ky", "f34 xero", "corrupt mods", "josh pvp", "fagatron", "ragarooo", "mr tempest14", "niggerslayer", "keith lemon", "corrupt modz",
			"hitler power", "ll", "ku klux clan", "jord lvl 3", "soa", "jord lvl 1", "zzzzzzzzzz", "bank acc", "sammy3636", "l0lnigga", "angel", "murmasa", "26 nigags",
			"cucking funt", "verbeemen", "me", "alvin", "bo2 legend", "zesus", "cum for you", "lance", "ihax", "selling porn", "amazon", "reptililan", "headkopf", "qqqquuuavvo",
			"keep it ez", "darren11", "malice", "kanient", "mithril gps", "pixel kid", "ilililil", "ollie", "niggerzz", "2 bad", "quavo gone", "pooppkz", "wiseoldman", "nutzu",
			"nanpoop74", "potatohead", "gasoline yum", "promethium", "i am black", "charmander", "skill epicly", "pappadoms", "lets talklol", "check blm", "gf your bonk", "os",
			"clair", "njr99", "o s sk i l l", "soeifhsgfdgd", "slobbin dino", "zach", "craigfitz", "irapeniggers", "milongana", "robertoo", "il rush u il", "r1", "dustlvl1", "pp",
			"unmute me", "r7", "king of duel", "drummerboy", "auxi", "king crooks", "profanity", "0           i", "brant", "iron mikeyjk", "mrswish", "lawllawllawl", "venge",
			"iron tone", "l          p", "yoeriwada", "qk", "l          q", "big qups", "promises", "numberpker", "mushroom", "dr fishy rs", "basic pure", "guiiliicii",
			"skt t1 faker", "mogistan", "notpicklleman", "rise exertz", "iron dzr", "collin", "lalalalala", "fuck jesus", "unknownskill", "kurre bomb", "pvm david", "dry gooch",
			"its nick", "callum isfat", "rawger", "achillesssss", "snoop doggs", "google again", "tehbluedude1", "get murked", "fu yong farm", "badbow111", "pizza nose37",
			"old man", "blex", "fuck you hah", "king cassidy", "dfghh", "bravey", "0       i", "kep", "erwfsdfsd", "george", "choob", "wet wizard", "velet purse", "ben17",
			"iliketurtles", "slipper", "spider lovin", "bridderman48", "invisionx", "apostrophe", "skillpure", "conjaf", "rapedatbirth", "answertheque", "bug", "cucking f",
			"gabbertje", "baby blue", "no1 skiller", "monster55", "ben13", "tonic1337", "clear vision", "v3", "bring fire29", "slay me", "big tits", "amazing", "maks alt", "sinx",
			"hissirr", "i just fish", "iddosserver", "rapedatnine", "i kill cows", "night shift", "jizzonu4dayz", "shawarma", "pizzanose37", "conor fatass", "wildy owns",
			"klatt95", "blue phat111", "i like fishy", "extract", "jasonxxxy", "dustskills4d", "chechebubble", "vp", "i dontglitc", "most tankful", "g r a p e s", "0          i",
			"kalphiteing", "pagauk", "im lost", "snack", "ertertre", "quadra", "baddy", "donkey", "get in troub", "cold", "inventory", "dirtydan1222", "juicy join", "dani",
			"just mad son", "jad nl", "y u no good", "laijl13", "sex addict", "not wortho", "15 hours gl", "thropical", "0          2", "cindy", "sarizenakaa", "instawantsm",
			"advertise", "i am bandos", "valrex", "cucking f5", "brent", "gf veldhar", "iron vagina", "knutted", "lkjdfklmjsqd", "manta rays", "greensnail", "its callan",
			"fuck owner", "spartangang", "onika", "9 tails", "flying pig", "dokuro chan", "nice legs", "sighh", "im savage", "kkz", "militant", "illiiiillid", "dustobby",
			"trippy hits", "tantric", "n e g r 0", "suicide note", "cocococoiico", "biggiee", "kushking", "exp 1", "ironrandynub", "herrodragon", "oglocoo2", "pk boss", "some1",
			"0riginal", "lift", "im a 5ner", "flux enigma", "nigger nami", "fluttershy", "zz", "i steal shit", "instawantsme", "off i team", "bart simpson", "setups nan", "yahama",
			"c u slave", "dark", "ikbenomer", "ipban pk p0ika", "get cancer", "iron niggers", "spasticpker", "glacial", "nigahigaz", "afk thieve", "reviv", "welph", "king ratooz",
			"omaigod", "po0o00o0", "hey bigga", "sakurasai", "naanis", "bossings", "bubba", "siinz fate", "svmu", "i hit u sit", "siufdifusddd", "corp", "not done yet", "corn",
			"rebuilding", "niggerkill", "cumlet loloo", "lemon13754", "dopiee", "rsw pegs", "naturee", "die staking", "couches123", "think melons", "matt skille", "solo 45",
			"gambles", "ironshadow", "humptydumpty", "wreck3d", "waste no xp", "ultimatzoid", "jamie", "the pluger", "l maniac", "master miles", "baby llama", "w r x", "xxshadow",
			"youtube", "here", "l     l   i", "gib", "iron maze", "69 nigger", "og savagee", "l3skill", "yushey", "1 spec 2 l8", "pussy juice", "stopbaninme", "jamie muted",
			"golems", "po0o00o0op", "sneakyscream", "ayy lmaoa", "player", "iron fang", "samppa", "dictator", "lmq chaox", "tacos4lyfe", "dkdjdkdjkdh", "shitserver12",
			"hatchetmane", "oglocoo", "l     l olll", "hella op ok", "lily", "dicely done1", "ilovepenis", "og bobby", "dicely done");

	private static int wiped = 0;

	public static void main(String... args) {
		Path directory = Paths.get("Data", "characters");

		System.out.println(USERS.size() + " character files to wipe.");

		Collection<File> characterFiles = FileUtils.listFiles(directory.toFile(), new String[] { "txt" }, true);

		System.out.println(characterFiles.size() + " character files found.");

		characterFiles = characterFiles.stream().filter(file -> USERS.stream().anyMatch(user -> user.equalsIgnoreCase(FilenameUtils.removeExtension(file.getName()))))
				.collect(Collectors.toList());

		characterFiles.forEach(WipeListedUsers::wipe);

		System.out.println(wiped + " character files have been wiped clean.");
	}

	private static void wipe(File file) {
		try {
			List<String> lines = Files.readAllLines(file.toPath());

			List<String> modified = lines.stream().filter(line -> UNMODIFIED_CONTENT.stream().anyMatch(l -> line.startsWith(l))).collect(Collectors.toList());

			Files.delete(file.toPath());

			Files.createFile(file.toPath());

			Files.write(file.toPath(), modified);

			wiped++;
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}
