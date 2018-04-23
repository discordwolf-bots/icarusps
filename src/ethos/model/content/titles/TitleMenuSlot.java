package ethos.model.content.titles;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

/**
 * Represents a single slot on the interface. Please note that although the slots are ordered in numberical order and that the button id's do have a pattern. However, in case of
 * change I have provided this enum.
 * 
 * @author Jason MacKeigan
 * @date Jan 22, 2015, 11:00:55 PM
 */
public enum TitleMenuSlot {
	SLOT_1(1, 33051, 129026), 
	SLOT_2(2, 33053, 129028), 
	SLOT_3(3, 33055, 129030), 
	SLOT_4(4, 33057, 129032), 
	SLOT_5(5, 33059, 129034), 
	SLOT_6(6, 33061, 129036), 
	SLOT_7(7, 33063, 129038), 
	SLOT_8(8, 33065, 129040), 
	SLOT_9(9, 33067, 129042), 
	SLOT_10(10, 33069, 129044), 
	SLOT_11(11, 33071, 129046), 
	SLOT_12(12, 33073, 129048), 
	SLOT_13(13, 33075, 129050), 
	SLOT_14(14, 33077, 129052), 
	SLOT_15(15, 33079, 129054), 
	SLOT_16(16, 33081, 129056), 
	SLOT_17(17, 33083, 129058), 
	SLOT_18(18, 33085, 129060), 
	SLOT_19(19, 33087, 129062), 
	SLOT_20(20, 33089, 129064), 
	SLOT_21(21, 33091, 129066), 
	SLOT_22(22, 33093, 129068), 
	SLOT_23(23, 33095, 129070), 
	SLOT_24(24, 33097, 129072), 
	SLOT_25(25, 33099, 129074),
	SLOT_26(26, 33101, 129076),
	SLOT_27(27, 33103, 129078),
	SLOT_28(28, 33105, 129080),
	SLOT_29(29, 33107, 129082),
	SLOT_30(30, 33109, 129084),
	SLOT_31(31, 33111, 129086),
	SLOT_32(32, 33113, 129088),
	SLOT_33(33, 33115, 129090),
	SLOT_34(34, 33117, 129092);

	/**
	 * The index on the menu this slot resides
	 */
	private final int index;

	/**
	 * The identification value of the component the string is displayed on
	 */
	private final int stringId;

	/**
	 * The button id that when clicked triggers an action
	 */
	private final int buttonId;

	/**
	 * Creates a new slot with an index and button id
	 * 
	 * @param index the index on the menu
	 * @param buttonId the button for triggering an action
	 */
	private TitleMenuSlot(int index, int stringId, int buttonId) {
		this.index = index;
		this.stringId = stringId;
		this.buttonId = buttonId;
	}

	/**
	 * Retrieves the index for this slot on the menu
	 * 
	 * @return the index on the menu
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * The identification value of the component the string is displayed on
	 * 
	 * @return the identification value
	 */
	public int getStringId() {
		return stringId;
	}

	/**
	 * The button id on the menu for this slot
	 * 
	 * @return the button id
	 */
	public int getButtonId() {
		return buttonId;
	}

	/**
	 * The slot with the equivellent button id
	 * 
	 * @param buttonId the button id
	 * @return A {@link TitleMenuSlot} object if the button matches any of the button values for any of the elements.
	 */
	public static TitleMenuSlot get(int buttonId) {
		return SLOTS.stream().filter(s -> s.buttonId == buttonId).findFirst().orElse(null);
	}

	/**
	 * A set of all elements in the {@linkplain TitleMenuSlot} enum.
	 */
	private static final Set<TitleMenuSlot> SLOTS = Collections.unmodifiableSet(EnumSet.allOf(TitleMenuSlot.class));

}
