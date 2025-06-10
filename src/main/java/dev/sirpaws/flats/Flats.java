package dev.sirpaws.flats;

import dev.sirpaws.flats.items.ItemRegistry;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Flats implements ModInitializer {
	public static final String MOD_ID = "dev-sirpaws-flats";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ItemRegistry.initialize();

		LOGGER.info("Hello Fabric world!");

		UseBlockCallback.EVENT.register(((player, world, hand, blockHitResult) -> {
			BlockState state = world.getBlockState(blockHitResult.getBlockPos());
			if (state.isOf(Blocks.SAND) && !player.isSpectator() && player.getMainHandStack().isEmpty()) {
				player.giveItemStack(new ItemStack(ItemRegistry.PILE_OF_SAND));
				return ActionResult.SUCCESS;
			}
			if (state.isOf(Blocks.RED_SAND) && !player.isSpectator() && player.getMainHandStack().isEmpty()) {
				player.giveItemStack(new ItemStack(ItemRegistry.PILE_OF_RED_SAND));
				return ActionResult.SUCCESS;
			}
			return ActionResult.PASS;
		}));
	}
}