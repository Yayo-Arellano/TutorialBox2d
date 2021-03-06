package com.tiarsoft.tutorialbox2d.tutoriales;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.tiarsoft.tutorialbox2d.Assets;
import com.tiarsoft.tutorialbox2d.MainBox2d;
import com.tiarsoft.tutorialbox2d.Screens;

/**
 * Friccion, densidad y restitucion
 * 
 * Puedes encontrar este tutorial en mi blog: http://tutoriales.tiarsoft.com/
 * 
 * @author Gerardo Arellano
 * 
 */

public class TutorialNo5 extends Screens {

	Box2DDebugRenderer renderer;
	World oWorld;

	public TutorialNo5(MainBox2d game) {
		super(game);
		Vector2 gravedad = new Vector2(0, -9.8f);
		boolean dormir = true;
		oWorld = new World(gravedad, dormir);
		renderer = new Box2DDebugRenderer();

		crearDynamic();
		crearStatic();
		crearKinematic();
	}

	private void crearStatic() {
		BodyDef bd = new BodyDef();
		bd.position.set(0, .5f);
		bd.type = BodyType.StaticBody;

		EdgeShape shape = new EdgeShape();
		shape.set(0, 0, WORLD_WIDTH, 0);

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;

		Body oBody = oWorld.createBody(bd);
		oBody.createFixture(fixDef);

		shape.dispose();

	}

	private void crearKinematic() {
		BodyDef bd = new BodyDef();
		bd.position.set(4, 1.5f);
		bd.type = BodyType.KinematicBody;

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(.1f, .75f);

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;

		Body oBody = oWorld.createBody(bd);
		oBody.createFixture(fixDef);

		shape.dispose();

		/*
		 * Hara que nuestro cuerpo kinematico gire en sentido contrario de las manecillas del reloj
		 */
		oBody.setAngularVelocity((float) Math.toRadians(360));
	}

	private void crearDynamic() {
		BodyDef bd = new BodyDef();
		bd.position.set(4, 4.5f);
		bd.type = BodyType.DynamicBody;

		CircleShape shape = new CircleShape();
		shape.setRadius(.25f);

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;

		Body oBody = oWorld.createBody(bd);
		oBody.createFixture(fixDef);
	}

	@Override
	public void update(float delta) {
		oWorld.step(delta, 8, 6);

	}

	@Override
	public void draw(float delta) {
		oCamUI.update();
		batcher.setProjectionMatrix(oCamUI.combined);

		batcher.begin();
		Assets.font.draw(batcher, "Fps:" + Gdx.graphics.getFramesPerSecond(),
				0, 20);
		batcher.end();

		oCamBox2D.update();
		renderer.render(oWorld, oCamBox2D.combined);

	}

}
