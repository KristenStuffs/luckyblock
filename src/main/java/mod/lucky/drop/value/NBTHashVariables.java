package mod.lucky.drop.value;

import java.util.ArrayList;
import java.util.Random;

import mod.lucky.drop.func.DropProcessData;
import mod.lucky.util.LuckyUtils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.loot.LootTables;
import net.minecraft.nbt.*;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.registries.ForgeRegistries;

public class NBTHashVariables {
    public static String[] NBT_HASH_VARIABLES = {
        "#luckySwordEnchantments",
        "#luckyAxeEnchantments",
        "#luckyToolEnchantments",
        "#luckyHelmetEnchantments",
        "#luckyLeggingsEnchantments",
        "#luckyBootsEnchantments",
        "#luckyBowEnchantments",
        "#luckyFishingRodEnchantments",
        "#luckyTridentEnchantments",
        "#luckyCrossbowEnchantments",
        "#randEnchantment",
        "#luckyPotionEffects",
        "#unluckyPotionEffects",
        "#randFireworksRocket",
        "#randLaunchMotion",
        "#motionFromDirection",
        "#bowMotion",
        "#chestLootTable"
    };

    private static final CompoundNBT protection = getEnchantment(Enchantments.PROTECTION, 4);
    private static final CompoundNBT fireProtection = getEnchantment(Enchantments.FIRE_PROTECTION, 4);
    private static final CompoundNBT featherFalling = getEnchantment(Enchantments.FEATHER_FALLING, 4);
    private static final CompoundNBT blastProtection = getEnchantment(Enchantments.BLAST_PROTECTION, 4);
    private static final CompoundNBT projectileProtection = getEnchantment(Enchantments.PROJECTILE_PROTECTION, 4);
    private static final CompoundNBT respiration = getEnchantment(Enchantments.RESPIRATION, 3);
    private static final CompoundNBT aquaAffinity = getEnchantment(Enchantments.AQUA_AFFINITY, 1);
    private static final CompoundNBT thorns = getEnchantment(Enchantments.THORNS, 3);

    private static final CompoundNBT sharpness = getEnchantment(Enchantments.SHARPNESS, 5);
    private static final CompoundNBT smite = getEnchantment(Enchantments.SMITE, 5);
    private static final CompoundNBT baneOfArthroponds = getEnchantment(Enchantments.BANE_OF_ARTHROPODS, 5);
    private static final CompoundNBT knockBack = getEnchantment(Enchantments.KNOCKBACK, 2);
    private static final CompoundNBT fireAspect = getEnchantment(Enchantments.FIRE_ASPECT, 2);
    private static final CompoundNBT looting = getEnchantment(Enchantments.LOOTING, 3);

    private static final CompoundNBT efficiency = getEnchantment(Enchantments.EFFICIENCY, 5);
    private static final CompoundNBT silkTouch = getEnchantment(Enchantments.SILK_TOUCH, 1);
    private static final CompoundNBT unbreaking = getEnchantment(Enchantments.UNBREAKING, 3);
    private static final CompoundNBT fortune = getEnchantment(Enchantments.FORTUNE, 3);

    private static final CompoundNBT power = getEnchantment(Enchantments.POWER, 5);
    private static final CompoundNBT punch = getEnchantment(Enchantments.PUNCH, 2);
    private static final CompoundNBT flame = getEnchantment(Enchantments.FLAME, 1);
    private static final CompoundNBT infinity = getEnchantment(Enchantments.INFINITY, 1);
    private static final CompoundNBT luckOfTheSea = getEnchantment(Enchantments.LUCK_OF_THE_SEA, 3);
    private static final CompoundNBT lure = getEnchantment(Enchantments.LURE, 3);

    private static final CompoundNBT loyalty = getEnchantment(Enchantments.LOYALTY, 3);
    private static final CompoundNBT channeling = getEnchantment(Enchantments.CHANNELING, 1);
    private static final CompoundNBT riptide = getEnchantment(Enchantments.RIPTIDE, 3);
    private static final CompoundNBT impaling = getEnchantment(Enchantments.IMPALING, 5);

    private static final CompoundNBT quickCharge = getEnchantment(Enchantments.QUICKCHARGE, 3);
    private static final CompoundNBT multishot = getEnchantment(Enchantments.MULTISHOT, 1);
    private static final CompoundNBT piercing = getEnchantment(Enchantments.PIERCING, 4);

    private static final CompoundNBT speed = getEffectInstance(Effects.SPEED, 3, 9600);
    private static final CompoundNBT slowness = getEffectInstance(Effects.SLOWNESS, 3, 9600);
    private static final CompoundNBT haste = getEffectInstance(Effects.HASTE, 3, 9600);
    private static final CompoundNBT miningFatigue = getEffectInstance(Effects.MINING_FATIGUE, 3, 9600);
    private static final CompoundNBT strength = getEffectInstance(Effects.STRENGTH, 3, 9600);
    private static final CompoundNBT instantHealth = getEffectInstance(Effects.INSTANT_HEALTH, 3, 0);
    private static final CompoundNBT instantDamage = getEffectInstance(Effects.INSTANT_DAMAGE, 3, 0);
    private static final CompoundNBT jumpBoost = getEffectInstance(Effects.JUMP_BOOST, 3, 9600);
    private static final CompoundNBT nausea = getEffectInstance(Effects.NAUSEA, 0, 9600);
    private static final CompoundNBT regeneration = getEffectInstance(Effects.REGENERATION, 3, 9600);
    private static final CompoundNBT resistance = getEffectInstance(Effects.RESISTANCE, 3, 9600);
    private static final CompoundNBT fireResistance = getEffectInstance(Effects.FIRE_RESISTANCE, 0, 9600);
    private static final CompoundNBT waterBreathing = getEffectInstance(Effects.WATER_BREATHING, 0, 9600);
    private static final CompoundNBT invisibility = getEffectInstance(Effects.INVISIBILITY, 0, 9600);
    private static final CompoundNBT blindness = getEffectInstance(Effects.BLINDNESS, 0, 9600);
    private static final CompoundNBT nightVision = getEffectInstance(Effects.NIGHT_VISION, 0, 9600);
    private static final CompoundNBT hunger = getEffectInstance(Effects.HUNGER, 3, 9600);
    private static final CompoundNBT weakness = getEffectInstance(Effects.WEAKNESS, 3, 9600);
    private static final CompoundNBT poison = getEffectInstance(Effects.POISON, 3, 9600);
    private static final CompoundNBT wither = getEffectInstance(Effects.WITHER, 3, 9600);
    private static final CompoundNBT healthBoost = getEffectInstance(Effects.HEALTH_BOOST, 3, 9600);
    private static final CompoundNBT absorbtion = getEffectInstance(Effects.ABSORPTION, 3, 9600);
    private static final CompoundNBT saturation = getEffectInstance(Effects.SATURATION, 3, 9600);

    private static Random random = new Random();

    private static CompoundNBT getEnchantment(final Enchantment enchantment, final int maxLevel) {
        final CompoundNBT nbttag = new CompoundNBT();

        nbttag.putString("id", ForgeRegistries.ENCHANTMENTS.getKey(enchantment).toString());
        nbttag.putShort("lvl", (short) maxLevel);

        return nbttag;
    }

    private static CompoundNBT getEffectInstance(final Effect potion, final int amplifier, final int duration) {
        final CompoundNBT nbttag = new CompoundNBT();
        final EffectInstance effect = new EffectInstance(potion, duration, amplifier);
        return effect.write(nbttag);
    }

    private static ArrayList<CompoundNBT> getRandomList(
        final int minAmount, final int maxAmount, final CompoundNBT... elements) {
        final int amountToRemove =
            elements.length - (random.nextInt((maxAmount + 1) - minAmount) + minAmount);

        final ArrayList<CompoundNBT> chosenElementList = new ArrayList<CompoundNBT>(elements.length);
        for (final CompoundNBT element : elements) {
            chosenElementList.add(element.copy());
        }

        for (int a = 0; a < amountToRemove; a++) {
            final int index = random.nextInt(chosenElementList.size());
            chosenElementList.remove(index);
        }

        return chosenElementList;
    }

    private static ListNBT getRandomEnchantmentList(
        final int minAmount, final int maxAmount, final CompoundNBT... enchantments) {
        final ArrayList<CompoundNBT> chosenEnchantments =
            getRandomList(minAmount, maxAmount, enchantments);

        final ListNBT nbttaglist = new ListNBT();
        for (final CompoundNBT enchantment : chosenEnchantments) {
            enchantment.putShort("lvl", (short) (random.nextInt(enchantment.getShort("lvl")) + 1));
            nbttaglist.add(enchantment);
        }

        return nbttaglist;
    }

    private static ListNBT getRandomEffectInstanceList(
        final int minAmount, final int maxAmount, final CompoundNBT... potionEffects) {
        final ArrayList<CompoundNBT> chosenEffectInstances =
            getRandomList(minAmount, maxAmount, potionEffects);

        final ListNBT nbttaglist = new ListNBT();
        for (final CompoundNBT potionEffect : chosenEffectInstances) {
            potionEffect.putByte(
                "Amplifier", (byte) (random.nextInt(potionEffect.getByte("Amplifier") + 1)));
            final int minDuration = (int) (potionEffect.getInt("Duration") / 3F);
            potionEffect.putInt(
                "Duration",
                random.nextInt((potionEffect.getInt("Duration") + 1) - minDuration) + minDuration);
            nbttaglist.add(potionEffect);
        }

        return nbttaglist;
    }

    public static INBT getNBTTagFromString(String name, final DropProcessData processData) {
        if (name.equals("#luckySwordEnchantments"))
            return getRandomEnchantmentList(4, 6,
                sharpness, smite, baneOfArthroponds, knockBack, fireAspect, looting, unbreaking);
        if (name.equals("#luckyAxeEnchantments"))
            return getRandomEnchantmentList(4, 6,
                sharpness, smite, baneOfArthroponds, efficiency, unbreaking, fortune);
        if (name.equals("#luckyToolEnchantments"))
            return getRandomEnchantmentList(2, 3, efficiency, unbreaking, fortune);

        if (name.equals("#luckyHelmetEnchantments"))
            return getRandomEnchantmentList(4, 6,
                protection,
                fireProtection,
                blastProtection,
                projectileProtection,
                respiration,
                aquaAffinity,
                unbreaking);
        if (name.equals("#luckyChestplateEnchantments"))
            return getRandomEnchantmentList(4, 6,
                protection,
                fireProtection,
                blastProtection,
                projectileProtection,
                thorns,
                unbreaking);
        if (name.equals("#luckyLeggingsEnchantments"))
            return getRandomEnchantmentList(4, 6,
                protection,
                fireProtection,
                blastProtection,
                projectileProtection,
                thorns,
                unbreaking);
        if (name.equals("#luckyBootsEnchantments"))
            return getRandomEnchantmentList(4, 6,
                protection,
                fireProtection,
                featherFalling,
                blastProtection,
                projectileProtection,
                thorns,
                unbreaking);

        if (name.equals("#luckyBowEnchantments"))
            return getRandomEnchantmentList(3, 5, unbreaking, power, punch, flame, infinity);
        if (name.equals("#luckyFishingRodEnchantments"))
            return getRandomEnchantmentList(2, 3, unbreaking, luckOfTheSea, lure);
        if (name.equals("#luckyTridentEnchantments"))
            return getRandomEnchantmentList(3, 5, loyalty, channeling, riptide, impaling, unbreaking);
        if (name.equals("#luckyCrossbowEnchantments"))
            return getRandomEnchantmentList(2, 4, quickCharge, multishot, piercing, unbreaking);


        if (name.equals("#randEnchantment"))
            return getRandomEnchantmentList(1, 1
                protection,
                fireProtection,
                featherFalling,
                blastProtection,
                projectileProtection,
                thorns,
                sharpness,
                smite,
                baneOfArthroponds,
                knockBack,
                fireAspect,
                looting,
                efficiency,
                silkTouch,
                unbreaking,
                fortune,
                power,
                punch,
                flame,
                infinity,
                luckOfTheSea,
                lure,
                loyalty,
                channeling,
                riptide,
                impaling,
                quickCharge,
                multishot,
                piercing);

        if (name.equals("#luckyPotionEffects"))
            return getRandomEffectInstanceList(7, 10,
                speed,
                haste,
                strength,
                instantHealth,
                jumpBoost,
                regeneration,
                resistance,
                fireResistance,
                waterBreathing,
                invisibility,
                nightVision,
                healthBoost,
                absorbtion,
                saturation);

        if (name.equals("#unluckyPotionEffects"))
            return getRandomEffectInstanceList(5, 7,
                slowness,
                miningFatigue,
                instantDamage,
                nausea,
                blindness,
                hunger,
                weakness,
                poison,
                wither);

        if (name.equals("#randFireworksRocket")) return LuckyUtils.getRandomFireworksRocket();

        if (name.startsWith("#randLaunchMotion")) {
            try {
                float launchPower = 0.9F;
                int launchAngle = 15;
                if (name.startsWith("#randLaunchMotion(")) {
                    final String contents = name.substring(name.indexOf('(') + 1, name.lastIndexOf(')'));
                    final String[] splitValue = DropStringUtils.splitBracketString(contents, ',');
                    splitValue[0] = DropStringUtils.removeNumSuffix(splitValue[0]);
                    launchPower = ValueParser.getFloat(splitValue[0], processData);
                    launchAngle = ValueParser.getInteger(splitValue[1], processData);
                }

                final float launchYaw = MathHelper.wrapDegrees(random.nextFloat() * 360.0F);
                final float launchPitch = -90.0F + (random.nextInt(launchAngle * 2) - launchAngle);
                final float launchMotionX =
                    -MathHelper.sin(launchYaw / 180.0F * (float) Math.PI)
                        * MathHelper.cos(launchPitch / 180.0F * (float) Math.PI)
                        * launchPower;
                final float launchMotionZ =
                    MathHelper.cos(launchYaw / 180.0F * (float) Math.PI)
                        * MathHelper.cos(launchPitch / 180.0F * (float) Math.PI)
                        * launchPower;
                final float launchMotionY = -MathHelper.sin(launchPitch / 180.0F * (float) Math.PI) * launchPower;

                final ListNBT motionList = new ListNBT();
                motionList.add(DoubleNBT.valueOf(launchMotionX));
                motionList.add(DoubleNBT.valueOf(launchMotionY));
                motionList.add(DoubleNBT.valueOf(launchMotionZ));
                return motionList;
            } catch (final Exception e) {
            }
        }

        if (name.startsWith("#motionFromDirection(")) {
            try {
                final String contents = name.substring(name.indexOf('(') + 1, name.lastIndexOf(')'));
                final String[] splitValue = DropStringUtils.splitBracketString(contents, ',');
                splitValue[2] = DropStringUtils.removeNumSuffix(splitValue[2]);

                final int launchYaw = ValueParser.getInteger(splitValue[0], processData);
                final int launchPitch = ValueParser.getInteger(splitValue[1], processData);
                final float launchPower = ValueParser.getFloat(splitValue[2], processData);
                final float launchMotionX =
                    -MathHelper.sin(launchYaw / 180.0F * (float) Math.PI)
                        * MathHelper.cos(launchPitch / 180.0F * (float) Math.PI)
                        * launchPower;
                final float launchMotionZ =
                    MathHelper.cos(launchYaw / 180.0F * (float) Math.PI)
                        * MathHelper.cos(launchPitch / 180.0F * (float) Math.PI)
                        * launchPower;
                final float launchMotionY = -MathHelper.sin(launchPitch / 180.0F * (float) Math.PI) * launchPower;

                final ListNBT motionList = new ListNBT();
                motionList.add(DoubleNBT.valueOf(launchMotionX));
                motionList.add(DoubleNBT.valueOf(launchMotionY));
                motionList.add(DoubleNBT.valueOf(launchMotionZ));
                return motionList;
            } catch (final Exception e) {
            }
        }

        if (name.startsWith("#bowMotion")) {
            try {
                float bowPowerMod = 1.0F;
                float randAngle = 0;
                if (name.startsWith("#bowMotion(")) {
                    final String contents = name.substring(name.indexOf('(') + 1, name.lastIndexOf(')'));
                    final String[] splitValue = DropStringUtils.splitBracketString(contents, ',');
                    splitValue[0] = DropStringUtils.removeNumSuffix(splitValue[0]);
                    if (splitValue.length > 1) splitValue[1] = DropStringUtils.removeNumSuffix(splitValue[1]);
                    bowPowerMod = ValueParser.getFloat(splitValue[0], processData);
                    if (splitValue.length > 1) randAngle = ValueParser.getFloat(splitValue[1], processData);
                }

                final Entity player = processData.getPlayer();
                final Vector3d playerPos = player.getPositionVec();

                final ArrowEntity arrowEntity = processData.getPlayer() instanceof LivingEntity
                    ? new ArrowEntity(processData.getWorld(), (LivingEntity) player)
                    : new ArrowEntity(processData.getWorld(), playerPos.x, playerPos.y, playerPos.z);

                arrowEntity.func_234612_a_( // setArrowMotion
                    player,
                    player.rotationPitch
                        + HashVariables.randomFloatClamp(
                            processData.getWorld().rand, -randAngle, randAngle),
                    player.rotationYaw
                        + HashVariables.randomFloatClamp(
                            processData.getWorld().rand, -randAngle, randAngle),
                    0.0F,
                    processData.getBowPower() * 3.0F * bowPowerMod,
                    1.0F);

                final ListNBT motionList = new ListNBT();
                final Vector3d arrowMotion = arrowEntity.getMotion();
                motionList.add(DoubleNBT.valueOf(arrowMotion.x));
                motionList.add(DoubleNBT.valueOf(arrowMotion.y));
                motionList.add(DoubleNBT.valueOf(arrowMotion.z));
                return motionList;
            } catch (final Exception e) {}
        }

        name =
            name.replace(
                "#chestVillageArmorer",
                "#chestLootTable(" + LootTables.CHESTS_VILLAGE_VILLAGE_ARMORER.getPath() + ")");
        name =
            name.replace(
                "#chestBonusChest",
                "#chestLootTable(" + LootTables.CHESTS_SPAWN_BONUS_CHEST.getPath() + ")");
        name =
            name.replace(
                "#chestDungeonChest",
                "#chestLootTable(" + LootTables.CHESTS_SIMPLE_DUNGEON.getPath() + ")");

        if (name.startsWith("#chestLootTable(")) {
            final ChestTileEntity tileEntityChest = new ChestTileEntity();
            final String lootId =
                ValueParser.getString(name.substring(name.indexOf('(') + 1, name.lastIndexOf(')')));
            tileEntityChest.setLootTable(new ResourceLocation("minecraft", lootId), random.nextLong());
            if (processData.getWorld() != null)
                tileEntityChest.setWorldAndPos(processData.getWorld(), processData.getHarvestBlockPos());
            tileEntityChest.fillWithLoot(null);

            final CompoundNBT tagCompound = new CompoundNBT();
            tileEntityChest.write(tagCompound);
            return tagCompound.getList("Items", Constants.NBT.TAG_COMPOUND);
        }

        return null;
    }
}