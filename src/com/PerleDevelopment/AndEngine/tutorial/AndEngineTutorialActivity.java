package com.PerleDevelopment.AndEngine.tutorial;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import com.PerleDevelopment.AndEngine.tutorial.helper.AccelerometerHelper;
import com.PerleDevelopment.AndEngine.tutorial.objects.Player;

public class AndEngineTutorialActivity extends SimpleBaseGameActivity {

	// ===========================================================
	// Constants
	// ===========================================================

	public static final int CAMERA_WIDTH = 480;
	public static final int CAMERA_HEIGHT = 800;

	// ===========================================================
	// Fields
	// ===========================================================

	private Camera mCamera;
	private AccelerometerHelper mAccelerometerHelper;
	private Scene mMainScene;

	private BitmapTextureAtlas mPlayerBitmapTextureAtlas;
	private TextureRegion mPlayerTextureRegion;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public EngineOptions onCreateEngineOptions() {
		mAccelerometerHelper = new AccelerometerHelper(this);
		
		this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

		return new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera);
	}

	@Override
	protected void onCreateResources() {
		/* Load all the textures this game needs. */
		this.mPlayerBitmapTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 32, 32);
		this.mPlayerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mPlayerBitmapTextureAtlas, this, "face_box.png", 0, 0);
		this.mPlayerBitmapTextureAtlas.load();
	}

	@Override
	protected Scene onCreateScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger()); // logs the frame rate

		/* Create Scene and set background colour to (1, 1, 1) = white */
		this.mMainScene = new Scene();
		this.mMainScene.setBackground(new Background(1, 1, 1));

		/* Centre the player on the camera. */
		final float centerX = (CAMERA_WIDTH - this.mPlayerTextureRegion.getWidth()) / 2;
		final float centerY = (CAMERA_HEIGHT - this.mPlayerTextureRegion.getHeight()) / 2;

		/* Create the sprite and add it to the scene. */
		final Player oPlayer = new Player(centerX, centerY, this.mPlayerTextureRegion, this.getVertexBufferObjectManager());
		this.mMainScene.attachChild(oPlayer);

		return this.mMainScene;
	}

}