package com.PerleDevelopment.AndEngine.tutorial.objects;

import java.util.ArrayList;

import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.util.Log;

import com.PerleDevelopment.AndEngine.tutorial.AndEngineTutorialActivity;
import com.PerleDevelopment.AndEngine.tutorial.helper.AccelerometerHelper;

public class Player extends GameObject {

	// ===========================================================
	// Constants
	// ===========================================================

	final int DEFAULT_VELOCITY = 200;

	// ===========================================================
	// Fields
	// ===========================================================

	boolean jumping = false;
	private ArrayList<Platform> mPlatforms;

	// ===========================================================
	// Constructors
	// ===========================================================

	public Player(final float pX, final float pY, final TextureRegion pTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public ArrayList<Platform> getmPlatforms() {
		return mPlatforms;
	}

	public void setmPlatforms(ArrayList<Platform> mPlatforms) {
		this.mPlatforms = mPlatforms;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void move() {

		this.mPhysicsHandler.setVelocityX(-AccelerometerHelper.TILT * DEFAULT_VELOCITY);
		setRotation(-AccelerometerHelper.TILT * 7);
		OutOfScreenX();

		Jumping();

		for (Platform Platform : getmPlatforms()) {
			if (this.collidesWith(Platform)) {
				Log.v("objects.Player", "just collided with platform ;)");
			}
		}

	}

	// ===========================================================
	// Methods
	// ===========================================================

	private void Jumping() {
		if (jumping) {
			Jump();
		} else {
			Fall();
		}
	}

	private void Jump() {
		if (mY <= AndEngineTutorialActivity.CAMERA_HEIGHT / 2) { // mY <= 400
			jumping = false;
		} else {
			this.mPhysicsHandler.setVelocityY(-DEFAULT_VELOCITY);
		}
	}

	private void Fall() {
		if (mY >= AndEngineTutorialActivity.CAMERA_HEIGHT) { // mY >= 800
			jumping = true;
		} else {
			this.mPhysicsHandler.setVelocityY(DEFAULT_VELOCITY);
		}
	}

	private void OutOfScreenX() {
		if (mX > AndEngineTutorialActivity.CAMERA_WIDTH) { // OutOfScreenX (right)
			mX = 0;
		} else if (mX < 0) { // OutOfScreenX (left)
			mX = AndEngineTutorialActivity.CAMERA_WIDTH;
		}
	}
}
