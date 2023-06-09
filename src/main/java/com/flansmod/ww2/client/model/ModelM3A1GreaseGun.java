//This File was created with the Minecraft-SMP Modelling Toolbox 1.5.4.1
// Copyright (C) 2013 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 3.0.x+

// Model Checklist
//    Model: 
//    - Check Left/Right    [ ]  (Left = + / Right = -)
//    - Code Cleaned        [ ]
//    - Coverted to Version [ ]

package com.flansmod.ww2.client.model;

import com.flansmod.client.model.EnumAnimationType;
import com.flansmod.client.model.ModelGun;
import com.flansmod.client.tmt.ModelRendererTurbo;

public class ModelM3A1GreaseGun extends ModelGun
{
	int textureX = 512;
	int textureY = 512;

	public ModelM3A1GreaseGun()	
	{


// Gun
		gunModel = new ModelRendererTurbo[9];
		gunModel[0] = new ModelRendererTurbo(this,  0,  300, textureX, textureY); // Grip
		gunModel[1] = new ModelRendererTurbo(this,  0,  290, textureX, textureY); // Body
		gunModel[2] = new ModelRendererTurbo(this,  0,  280, textureX, textureY); // Barrel
		gunModel[3] = new ModelRendererTurbo(this,  0,  270, textureX, textureY); // UnderBarrel
		gunModel[4] = new ModelRendererTurbo(this,  0,  260, textureX, textureY); // TriggerGuard
		gunModel[5] = new ModelRendererTurbo(this,  0,  250, textureX, textureY); // Wirestock
		gunModel[6] = new ModelRendererTurbo(this,  15,  250, textureX, textureY); // WirestockEnd
		gunModel[7] = new ModelRendererTurbo(this,  0,  240, textureX, textureY); // StightFront
		gunModel[8] = new ModelRendererTurbo(this,  0,  230, textureX, textureY); // SightRear

		gunModel[0].addBox(0F, 0F, 0F, 2, 4, 2, 0F); // Grip
		gunModel[0].setRotationPoint(0F, -4F, -1F);
		gunModel[0].rotateAngleZ = -0.1919862F;

		gunModel[1].addBox(0F, 0F, 0F, 13, 2, 2, 0F); // Body
		gunModel[1].setRotationPoint(-1F, -5F, -1F);

		gunModel[2].addBox(0F, 0F, 0F, 5, 1, 1, 0F); // Barrel
		gunModel[2].setRotationPoint(12F, -4.5F, -0.5F);

		gunModel[3].addBox(0F, 0F, 0F, 7, 1, 1, 0F); // UnderBarrel
		gunModel[3].setRotationPoint(1F, -3F, -0.5F);

		gunModel[4].addBox(0F, 0F, 0F, 3, 1, 1, 0F); // TriggerGuard
		gunModel[4].setRotationPoint(4F, -2.5F, -0.5F);

		gunModel[5].addBox(0F, 0F, 0F, 5, 0, 1, 0F); // Wirestock
		gunModel[5].setRotationPoint(-6F, -3.5F, -0.5F);

		gunModel[6].addBox(0F, 0F, 0F, 0, 3, 1, 0F); // WirestockEnd
		gunModel[6].setRotationPoint(-5.9F, -4F, -0.5F);
		gunModel[6].rotateAngleZ = -0.1047198F;

		gunModel[7].addBox(0F, 0F, 0F, 1, 1, 1, 0F); // StightFront
		gunModel[7].setRotationPoint(10.5F, -5.7F, -0.5F);
		gunModel[7].rotateAngleZ = 0.122173F;

		gunModel[8].addBox(0F, 0F, 0F, 1, 1, 1, 0F); // SightRear
		gunModel[8].setRotationPoint(0F, -5.5F, -0.5F);



// Ammo
		ammoModel = new ModelRendererTurbo[2];
		ammoModel[0] = new ModelRendererTurbo(this,  10,  230, textureX, textureY); // Magazine
		ammoModel[1] = new ModelRendererTurbo(this,  15,  230, textureX, textureY); // Magazine

		ammoModel[0].addBox(8F, -3F, -0.5F, 1, 6, 1, 0F); // Magazine
		ammoModel[0].setRotationPoint(0F, 0F, 0F);

		ammoModel[1].addBox(8.5F, -3F, -0.5F, 1, 6, 1, 0F); // Magazine
		ammoModel[1].setRotationPoint(0F, 0F, 0F);

		


		gunSlideDistance = 0F;
		animationType = EnumAnimationType.BOTTOM_CLIP;

		flipAll();
		translateAll(0F, 1F, 0F);
	}
}