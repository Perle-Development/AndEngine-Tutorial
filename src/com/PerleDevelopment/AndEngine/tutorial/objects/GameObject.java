package com.PerleDevelopment.AndEngine.tutorial.objects;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public abstract class GameObject extends Sprite {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	public PhysicsHandler mPhysicsHandler;

	// ===========================================================
	// Constructors
	// ===========================================================

	public GameObject(final float pX, final float pY, final TextureRegion pTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.mPhysicsHandler = new PhysicsHandler(this);
		this.registerUpdateHandler(this.mPhysicsHandler);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		move();

		super.onManagedUpdate(pSecondsElapsed);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public abstract void move();
}