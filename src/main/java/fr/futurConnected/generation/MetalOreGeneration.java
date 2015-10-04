package fr.futurConnected.generation;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import fr.futurConnected.FuturConnected;
import fr.futurConnected.blocks.BlockMetalOre;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.server.management.ItemInWorldManager;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class MetalOreGeneration implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if(world.provider.dimensionId == 0){
			generateOverworld(world, random, chunkX, chunkZ);
		}
	}
	
	public void generateOverworld(World world, Random rand, int x, int z){
		generateOre(FuturConnected.blockMetalOre, world, rand, x, z, 10, 64, 5, 5, 100, Blocks.stone);
	}
	
	public void generateOre(Block block, World world, Random random, int chunkX, int chunkZ, int minVienSize, int maxVienSize, int chance, int minY, int maxY, Block generateIn){
		
		int vienSize = minVienSize + random.nextInt(maxVienSize - minVienSize);
		int heightRange = maxY - minY;
		WorldGenMinable gen = new WorldGenMinable(block, vienSize, generateIn);
		for(int i = 0; i < chance; i++){
			int xRand = chunkX * 16 + random.nextInt(16);
			int yRand = random.nextInt(heightRange)+minY;
			int zRand = chunkZ * 16 + random.nextInt(16);
			gen.generate(world, random, xRand, yRand, zRand);
		}
	}
}