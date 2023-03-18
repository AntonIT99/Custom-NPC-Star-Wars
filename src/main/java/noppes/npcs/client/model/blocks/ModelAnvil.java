// Date: 11-10-2012 19:29:20
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package noppes.npcs.client.model.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAnvil extends ModelBase {
	ModelRenderer Tail;
	ModelRenderer Nose1;
	ModelRenderer Nose2;
	ModelRenderer Nose3;
	ModelRenderer Nose4;
	ModelRenderer Head1;
	ModelRenderer Head2;
	ModelRenderer Neck2;
	ModelRenderer Bottom2;
	ModelRenderer Bottom3;
	ModelRenderer Foot4;

	public ModelAnvil() {
		textureWidth = 64;
		textureHeight = 32;

		Tail = new ModelRenderer(this, 0, 0);
		Tail.addBox(0F, 0F, 0F, 1, 2, 4);
		Tail.setRotationPoint(-7F, 12F, -2F);

		Nose1 = new ModelRenderer(this, 0, 0);
		Nose1.addBox(0F, 0F, 0F, 1, 5, 6);
		Nose1.setRotationPoint(6F, 10F, -3F);

		Nose2 = new ModelRenderer(this, 0, 0);
		Nose2.addBox(0F, 0F, 0F, 1, 4, 5);
		Nose2.setRotationPoint(7F, 10F, -2.5F);

		Nose3 = new ModelRenderer(this, 0, 0);
		Nose3.addBox(0F, 0F, 0F, 1, 3, 4);
		Nose3.setRotationPoint(8F, 10F, -2F);

		Nose4 = new ModelRenderer(this, 0, 0);
		Nose4.addBox(0F, 0F, 0F, 1, 2, 2);
		Nose4.setRotationPoint(9F, 10F, -1F);

		Head1 = new ModelRenderer(this, 0, 0);
		Head1.addBox(0F, 0F, 0F, 12, 4, 7);
		Head1.setRotationPoint(-6F, 12F, -3.5F);

		Head2 = new ModelRenderer(this, 0, 0);
		Head2.addBox(0F, 0F, 0F, 14, 2, 9);
		Head2.setRotationPoint(-8F, 10F, -4.5F);

		Neck2 = new ModelRenderer(this, 0, 0);
		Neck2.addBox(0F, 0F, 0F, 10, 1, 6);
		Neck2.setRotationPoint(-5F, 16F, -3F);

		Bottom2 = new ModelRenderer(this, 0, 0);
		Bottom2.addBox(0F, 0F, 0F, 10, 2, 7);
		Bottom2.setRotationPoint(-5F, 20F, -3.5F);

		Bottom3 = new ModelRenderer(this, 0, 0);
		Bottom3.addBox(0F, 0F, 0F, 8, 3, 4);
		Bottom3.setRotationPoint(-4F, 17F, -2F);

		Foot4 = new ModelRenderer(this, 0, 0);
		Foot4.addBox(0F, 0F, 0F, 14, 2, 10);
		Foot4.setRotationPoint(-7F, 22F, -5F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5,entity);
		Tail.render(f5);
		Nose1.render(f5);
		Nose2.render(f5);
		Nose3.render(f5);
		Nose4.render(f5);
		Head1.render(f5);
		Head2.render(f5);
		Neck2.render(f5);
		Bottom2.render(f5);
		Bottom3.render(f5);
		Foot4.render(f5);
	}
}
