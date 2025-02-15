package mod.lucky.drop.func;

import mod.lucky.drop.DropSingle;
import mod.lucky.entity.EntityLuckyPotion;
import mod.lucky.entity.EntityLuckyProjectile;
import mod.lucky.network.PacketHandler;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.Constants;

public class DropFuncEntity extends DropFunc {
    @Override
    public void process(DropProcessData processData) {
        DropSingle drop = processData.getDropSingle();

        float posX = drop.getPropertyFloat("posX");
        float posY = drop.getPropertyFloat("posY");
        float posZ = drop.getPropertyFloat("posZ");

        if (posY <= -1) return;

        CompoundNBT nbtTagCompound =
            drop.getPropertyNBT("NBTTag") == null
                ? new CompoundNBT()
                : drop.getPropertyNBT("NBTTag");

        String id = drop.getPropertyString("ID");
        if (!processData.getWorld().isRemote()) {
            if (id.equals("lightning_bolt") || id.equals("LightningBolt")) {
                LightningBoltEntity lightningBolt = EntityType.LIGHTNING_BOLT.create(processData.getWorld());
                lightningBolt.moveForced(posX, posY, posZ);
                processData.getWorld().addEntity(lightningBolt);

            } else {
                nbtTagCompound.putString("id", id);
                spawnEntity(processData, nbtTagCompound, (ServerWorld) processData.getWorld(), posX, posY, posZ);
            }
        }
    }

    private static Entity spawnEntity(DropProcessData processData, CompoundNBT tag,
        ServerWorld world, double posX, double posY, double posZ) {

        // adjust height
        for (int y = 0; y < 10; y++) {
            if (processData.getWorld().isAirBlock(new BlockPos(posX, posY + y, posZ))) {
                posY += y;
                break;
            }
        }

        if (tag.contains("Pos")) {
            ListNBT posList = tag.getList("Pos", Constants.NBT.TAG_DOUBLE);
            posX = posList.getDouble(0);
            posY = posList.getDouble(1);
            posZ = posList.getDouble(2);
        }

        final Vector3d entityPos = new Vector3d(posX, posY, posZ);
        Entity entity = EntityType.loadEntityAndExecute(tag, world, e -> {
            e.setLocationAndAngles(entityPos.x, entityPos.y, entityPos.z,
                e.rotationYaw, e.rotationPitch);
            return !world.summonEntity(e) ? null : e;
        });

        if (entity == null) return null;
        else if (entity instanceof FallingBlockEntity && !tag.contains("Time"))
            ((FallingBlockEntity) entity).fallTime = 1;
        else if (entity instanceof EntityLuckyProjectile)
            ((EntityLuckyProjectile) entity).setShooter(processData.getPlayer());
        else if (entity instanceof ArrowEntity)
            ((ArrowEntity) entity).setShooter(processData.getPlayer());

        // randomize entity
        if (entity instanceof MobEntity
            && processData.getProcessType() != DropProcessData.EnumProcessType.LUCKY_STRUCT
            && !tag.contains("Passengers")) {

            ((MobEntity) entity).onInitialSpawn(world,
                world.getDifficultyForLocation(new BlockPos(entity.getPositionVec())),
                SpawnReason.EVENT,
                null, null);
            ((MobEntity) entity).readAdditional(tag);
        }

        if (entity instanceof EntityLuckyPotion || entity instanceof EntityLuckyProjectile) {
            PacketHandler.spawnEntity(entity);
        }

        return entity;
    }

    @Override
    public void registerProperties() {
        DropSingle.setDefaultProperty(this.getType(), "NBTTag", CompoundNBT.class, null);
        DropSingle.setDefaultProperty(this.getType(), "posX", Float.class, 0.0F);
        DropSingle.setDefaultProperty(this.getType(), "posY", Float.class, 0.0F);
        DropSingle.setDefaultProperty(this.getType(), "posZ", Float.class, 0.0F);
        DropSingle.setDefaultProperty(this.getType(), "posOffsetX", Float.class, 0.0F);
        DropSingle.setDefaultProperty(this.getType(), "posOffsetY", Float.class, 0.0F);
        DropSingle.setDefaultProperty(this.getType(), "posOffsetZ", Float.class, 0.0F);
    }

    @Override
    public String getType() {
        return "entity";
    }
}
