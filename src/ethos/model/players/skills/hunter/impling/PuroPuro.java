package ethos.model.players.skills.hunter.impling;

import ethos.model.players.Player;

public class PuroPuro {
	
	/**
	 * Impling data
	 * id -> The id of the impling
	 * x -> The x coordinate of the impling
	 * y -> The y coordinate of the impling
	 */
	public static final int[][] IMPLINGS = { // new impling area 3040, 4535
			/**
			 * Baby imps
			 */
			{1635, 3050, 4492},
			{1635, 3049, 4490},
			{1635, 3047, 4491},
			{1635, 3046, 4493},
			{1635, 3048, 4496},
			{1635, 3053, 4494},
			{1635, 3054, 4491},
			{1635, 3044, 4491},
			{1635, 3045, 4493},
			{1635, 3043, 4449},
			{1635, 3040, 4500},

			/**
			 * Young imps
			 */
			{1636, 3032, 4495},
			{1636, 3031, 4493},
			{1636, 3029, 4494},
			{1636, 3029, 4492},
			{1636, 3027, 4493},
			{1636, 3027, 4496},
			{1636, 3028, 4500},
			{1636, 3026, 4500},
			{1636, 3024, 4502},

			/**
			 * Gourment imps
			 */
			{1637, 3020, 4498},
			{1637, 3019, 4501},
			{1637, 3020, 4503},
			{1637, 3023, 4503},
			{1637, 3027, 4504},
			{1637, 3030, 4503},

			/**
			 * Earth imps
			 */
			{1638, 3039, 4499},
			{1638, 3037, 4499},
			{1638, 3035, 4497},
			{1638, 3034, 4497},
			{1638, 3034, 4499},
			{1638, 3036, 4502},

			/**
			 * Essence imps
			 */
			{1639, 3035, 4505},
			{1639, 3032, 4505},
			{1639, 3030, 4505},
			{1639, 3033, 4507},
			{1639, 3036, 4507},

			/**
			 * Eclectic imps
			 */
			{1640, 3026, 4508},
			{1640, 3023, 4508},
			{1640, 3019, 4508},
			{1640, 3022, 4510},
			{1640, 3025, 4511},

			/**
			 * Spirit imps
			 */

			/**
			 * Nature imps
			 */
			{1641, 3026, 4512},
			{1641, 3028, 4512},
			{1641, 3031, 4512},
			{1641, 3033, 4512},
			{1641, 3035, 4511},

			/**
			 * Magpie imps
			 */
			{1642, 3020, 4511},
			{1642, 3021, 4512},
			{1642, 3019, 4512},
			{1642, 3018, 4513},
			{1642, 3020, 4515},

			/**
			 * Ninja imps
			 */
			{1643, 3032, 4517},
			{1643, 3029, 4518},
			{1643, 3028, 4419},
			{1643, 3027, 4517},
			{1643, 3026, 4516},

			/**
			 * Dragon imps
			 */
			{1654, 3021, 4513},
			{1654, 3019, 4511},
			{1654, 3019, 4512},
			{1654, 3019, 4515},
			{1654, 3023, 4518},
	};
	
	public static void magicalWheat(Player player) {
		int x = player.absX, x2 = x;
		int y = player.absY, y2 = y;
		
		switch (x) {
		case 2584:
			x2 = 2582;
			break;
		case 2582:
			x2 = 2584;
			break;
		case 2599:
			x2 = 2601;
			break;
		case 2601:
			x2 = 2599;
			break;
			
		case 2581:
			x2 = 2579;
			break;
		case 2579:
			x2 = 2581;
			break;
		case 2602:
			x2 = 2604;
			break;
		case 2604:
			x2 = 2602;
			break;
			
		case 2578:
			x2 = 2576;
			break;
		case 2576:
			x2 = 2578;
			break;
		case 2605:
			x2 = 2607;
			break;
		case 2607:
			x2 = 2605;
			break;
			
		case 2575:
			x2 = 2573;
			break;
		case 2573:
			x2 = 2575;
			break;
		case 2608:
			x2 = 2610;
			break;
		case 2610:
			x2 = 2608;
			break;
			
		case 2572:
			x2 = 2570;
			break;
		case 2570:
			x2 = 2572;
			break;
		case 2611:
			x2 = 2613;
			break;
		case 2613:
			x2 = 2611;
			break;
			
		case 2569:
			x2 = 2567;
			break;
		case 2567:
			x2 = 2569;
			break;
		case 2614:
			x2 = 2616;
			break;
		case 2616:
			x2 = 2614;
			break;
			
		case 2566:
			x2 = 2564;
			break;
		case 2564:
			x2 = 2566;
			break;
		case 2617:
			x2 = 2619;
			break;
		case 2619:
			x2 = 2617;
			break;
		}
		
		switch (y) {
		case 4312:
			y2 = 4310;
			break;
		case 4310:
			y2 = 4312;
			break;
		case 4327:
			y2 = 4329;
			break;
		case 4329:
			y2 = 4327;
			break;
			
		case 4309:
			y2 = 4307;
			break;
		case 4307:
			y2 = 4309;
			break;
		case 4330:
			y2 = 4332;
			break;
		case 4332:
			y2 = 4330;
			break;
			
		case 4306:
			y2 = 4304;
			break;
		case 4304:
			y2 = 4306;
			break;
		case 4333:
			y2 = 4335;
			break;
		case 4335:
			y2 = 4333;
			break;
			
		case 4303:
			y2 = 4301;
			break;
		case 4301:
			y2 = 4303;
			break;
		case 4336:
			y2 = 4338;
			break;
		case 4338:
			y2 = 4336;
			break;
			
		case 4300:
			y2 = 4298;
			break;
		case 4298:
			y2 = 4300;
			break;
		case 4339:
			y2 = 4341;
			break;
		case 4341:
			y2 = 4339;
			break;
			
		case 4297:
			y2 = 4295;
			break;
		case 4295:
			y2 = 4297;
			break;
			
		case 4345:
			y2 = 4347;
			break;
		case 4347:
			y2 = 4345;
			break;
		}
		x2 -= x;
		y2 -= y;
		if (System.currentTimeMillis() - player.lastWheatPass < 2000) {
			return;
		}
		player.sendMessage("You use your strength to push through the wheat.");
		final int goX = x2, goY = y2;
		
		player.getAgilityHandler().move(player, goX, goY, 6594, -1);
		player.lastWheatPass = System.currentTimeMillis();
	}
}
