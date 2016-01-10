#include <Ogre/Ogre.h>
#include <OIS/OIS.h>

class frameapp : public Ogre::FrameListener
{
private:
	OIS::InputManager *in_mgr;
	OIS::Keyboard *keyboard;
	OIS::Mouse *mouse;
	Ogre::Camera *cam;
	float mov_speed,walking_speed,rotation,cam_speed;
	Ogre::Viewport *viewport;
	bool comp1,comp2,comp3;
	bool down1,down2,down3;
	Ogre::SceneNode *node;
	Ogre::AnimationState *anistatebottom,*anistatetop;
	Ogre::Entity *ent;
public:
	frameapp(Ogre::RenderWindow *win,Ogre::Camera *_cam,Ogre::Viewport *vp,Ogre::SceneNode *n,Ogre::Entity *entity)
	{
		node=n;
		ent=entity;
		walking_speed=30.0f;rotation=0;
		cam=_cam;
		mov_speed=50.0f;
		viewport=vp;
		comp1=comp2=comp3=down1=down2=down3=false;
		OIS::ParamList parameters;
		unsigned int window_hnd;
		std::ostringstream window_hnd_str;

		win->getCustomAttribute("WINDOW", &window_hnd);
		window_hnd_str<<window_hnd;

		parameters.insert(std::make_pair("WINDOW",window_hnd_str.str()));
		in_mgr=OIS::InputManager::createInputSystem(parameters);
		keyboard=static_cast<OIS::Keyboard*>(in_mgr->createInputObject(OIS::OISKeyboard , false ));
		mouse=static_cast<OIS::Mouse*>(in_mgr->createInputObject(OIS::OISMouse , false ));
		anistatebottom=ent->getAnimationState("RunBase");
		anistatebottom->setLoop(false);
		anistatetop=ent->getAnimationState("RunTop");
		anistatetop->setLoop(false);
	}

	~frameapp()
	{
		in_mgr->destroyInputObject(keyboard);
		in_mgr->destroyInputObject(mouse);
		OIS::InputManager::destroyInputSystem(in_mgr);
	}

	bool frameStarted(const Ogre::FrameEvent &evt)
	{
		cam_speed=1;
		bool walked=false;
		Ogre::Vector3 sintrans(0,0,0);
		Ogre::Vector3 translate(0,0,0);
		keyboard->capture();
		if(keyboard->isKeyDown(OIS::KC_LSHIFT))
		{
			cam_speed=20;
		}
		if(keyboard->isKeyDown(OIS::KC_W))
		{
			translate+=Ogre::Vector3(0,0,-1);
		}
		if(keyboard->isKeyDown(OIS::KC_S))
		{
			translate+=Ogre::Vector3(0,0,1);
		}
		if(keyboard->isKeyDown(OIS::KC_A))
		{
			translate+=Ogre::Vector3(-1,0,0);
		}
		if(keyboard->isKeyDown(OIS::KC_D))
		{
			translate+=Ogre::Vector3(1,0,0);
		}
		if(keyboard->isKeyDown(OIS::KC_ESCAPE))
		{
			return false;
		}
		if(keyboard->isKeyDown(OIS::KC_1) && !down1)
		{
			down1=true;
			comp1=!comp1;
			Ogre::CompositorManager::getSingleton().setCompositorEnabled(viewport,"Compositor_grey",comp1);
		}
		if(keyboard->isKeyDown(OIS::KC_2) && !down2)
		{
			down2=true;
			comp2=!comp2;
			Ogre::CompositorManager::getSingleton().setCompositorEnabled(viewport,"Compositor_invert",comp2);
		}
		if(keyboard->isKeyDown(OIS::KC_3) && !down3)
		{
			down3=true;
			comp3=!comp3;
			Ogre::CompositorManager::getSingleton().setCompositorEnabled(viewport,"pc_comp",comp3);
		}
		if(!keyboard->isKeyDown(OIS::KC_1))
		{
			down1=false;
		}
		if(!keyboard->isKeyDown(OIS::KC_2))
		{
			down2=false;
		}
		if(!keyboard->isKeyDown(OIS::KC_3))
		{
			down3=false;
		}
		if(keyboard->isKeyDown(OIS::KC_UP))
		{
			sintrans+=Ogre::Vector3(0,0,-1);
			rotation=3.14;
			walked=true;
		}
		if(keyboard->isKeyDown(OIS::KC_DOWN))
		{
			sintrans+=Ogre::Vector3(0,0,1);
			rotation=0;
			walked=true;
		}
		if(keyboard->isKeyDown(OIS::KC_LEFT))
		{
			sintrans+=Ogre::Vector3(-1,0,0);
			rotation=-1.57;
			walked=true;
		}
		if(keyboard->isKeyDown(OIS::KC_RIGHT))
		{
			sintrans+=Ogre::Vector3(1,0,0);
			rotation=1.57;
			walked=true;
		}
		if(walked)
		{
			anistatebottom->setEnabled(true);
			anistatetop->setEnabled(true);
			if(anistatebottom->hasEnded())
			{
				anistatebottom->setTimePosition(0.0f);
			}
			if(anistatetop->hasEnded())
			{
				anistatetop->setTimePosition(0.0f);
			}
		}
		else
		{
			anistatebottom->setEnabled(false);
			anistatebottom->setTimePosition(0.0f);
			anistatetop->setEnabled(false);
			anistatetop->setTimePosition(0.0f);
		}
		anistatebottom->addTime(evt.timeSinceLastFrame);
		anistatetop->addTime(evt.timeSinceLastFrame);
		cam->moveRelative(translate*evt.timeSinceLastFrame*mov_speed*cam_speed);
		node->translate(sintrans*evt.timeSinceLastFrame*walking_speed);
		node->resetOrientation();
		node->yaw(Ogre::Radian(rotation));
		mouse->capture();
		float rotX=mouse->getMouseState().X.rel*evt.timeSinceLastFrame*-1;
		float rotY=mouse->getMouseState().Y.rel*evt.timeSinceLastFrame*-1;
		cam->yaw(Ogre::Radian(rotX/10));
		cam->pitch(Ogre::Radian(rotY/10));
		return true;
	}
	/*bool frameRenderingQueued(const Ogre::FrameEvent &evt)
	{
		return true;
	}
	bool frameEnded(const Ogre::FrameEvent &evt)
	{
		return true;
	}*/
};

class exampleapp
{
private:
	Ogre::Root *root;
	Ogre::SceneManager *smgr;
	frameapp *flistener;
	bool keeprunning;
	Ogre::SceneNode *node;
	Ogre::Entity *ent;
	//Ogre::RenderWindow *window;
	//Ogre::Camera *cam;
	//Ogre::Viewport *vp;
public:
	exampleapp()
	{
		root=NULL;
		smgr=NULL;
		flistener=NULL;
	}
	~exampleapp()
	{
		delete flistener;
		delete root;
	}

	void createScene()
	{
		ent=smgr->createEntity("Sinbad.mesh");
		node=smgr->getRootSceneNode()->createChildSceneNode();
		node->attachObject(ent);

		Ogre::Plane plane(Ogre::Vector3::UNIT_Y,-5);
		Ogre::MeshManager::getSingleton().createPlane("plane",Ogre::ResourceGroupManager::DEFAULT_RESOURCE_GROUP_NAME,plane,2000,2000,200,200,true,1,5,5,Ogre::Vector3::UNIT_Z);
		Ogre::Entity *ground=smgr->createEntity("lightplane","plane");
		smgr->getRootSceneNode()->createChildSceneNode()->attachObject(ground);
		ground->setMaterialName("Examples/BeachStones");

		Ogre::Light *light=smgr->createLight("light1");
		light->setType(Ogre::Light::LT_DIRECTIONAL);
		light->setDirection(Ogre::Vector3(1,-1,0));
		smgr->setShadowTechnique(Ogre::SHADOWTYPE_STENCIL_ADDITIVE);

		Ogre::SceneNode* node1 = smgr->getRootSceneNode()->createChildSceneNode(Ogre::Vector3(0,10,0));
		Ogre::SceneNode* node2 = smgr->getRootSceneNode()->createChildSceneNode(Ogre::Vector3(10,11,0));
		Ogre::SceneNode* node3 = smgr->getRootSceneNode()->createChildSceneNode(Ogre::Vector3(20,9,0));
		Ogre::SceneNode* node4 = smgr->getRootSceneNode()->createChildSceneNode(Ogre::Vector3(-10,11,0));
		Ogre::SceneNode* node5 = smgr->getRootSceneNode()->createChildSceneNode(Ogre::Vector3(-20,19,0));

		Ogre::ParticleSystem *particlesys1=smgr->createParticleSystem("Smoke1","myparticle1");
		node1->attachObject(particlesys1);
		Ogre::ParticleSystem *particlesys2=smgr->createParticleSystem("Smoke2","myparticle1");
		node2->attachObject(particlesys2);
		Ogre::ParticleSystem *particlesys3=smgr->createParticleSystem("Smoke3","myparticle1");
		node3->attachObject(particlesys3);
		Ogre::ParticleSystem *particlesys4=smgr->createParticleSystem("Smoke4","myparticle1");
		node4->attachObject(particlesys4);
		Ogre::ParticleSystem *particlesys5=smgr->createParticleSystem("Smoke5","myparticle1");
		node5->attachObject(particlesys5);
	}

	void loadResources()
	{
		Ogre::ConfigFile cf;
		cf.load("../../bin/debug/resources_d.cfg");
		Ogre::ConfigFile::SectionIterator sec_itr=cf.getSectionIterator();
		Ogre::String sec_name,type_name,data_name;

		while(sec_itr.hasMoreElements())
		{
			sec_name=sec_itr.peekNextKey();
			Ogre::ConfigFile::SettingsMultiMap *settings=sec_itr.getNext();
			Ogre::ConfigFile::SettingsMultiMap::iterator i;
			for(i=settings->begin();i!=settings->end();i++)
			{
				type_name=i->first;
				data_name=i->second;
				Ogre::ResourceGroupManager::getSingleton().addResourceLocation(data_name,type_name,sec_name);
			}
		}
		Ogre::ResourceGroupManager::getSingleton().initialiseAllResourceGroups();
	}

	void renderOneFrame()
	{
		Ogre::WindowEventUtilities::messagePump();
		keeprunning=root->renderOneFrame();
	}

	bool fkeeprunning()
	{
		return keeprunning;
	}

	int startup()
	{
		root=new Ogre::Root("plugins_d.cfg");
		if(! (root->showConfigDialog() ))
		{
			return -1;
		}

		smgr=root->createSceneManager(Ogre::ST_GENERIC);
		Ogre::RenderWindow *window=root->initialise(true,"bharath");
		Ogre::Camera *cam=smgr->createCamera("bharathsn");
		cam->setPosition(0,0,50);
		cam->lookAt(0,0,0);
		cam->setNearClipDistance(5);
		
		Ogre::Viewport *vp=window->addViewport(cam);
		vp->setBackgroundColour(Ogre::ColourValue(0,0,0));
		cam->setAspectRatio((Ogre::Real)vp->getActualWidth() / (Ogre::Real) vp->getActualHeight());
		loadResources();
		createScene();
		flistener=new frameapp(window,cam,vp,node,ent);
		root->addFrameListener(flistener);

		Ogre::CompositorManager::getSingleton().addCompositor(vp,"Compositor_grey");
		Ogre::CompositorManager::getSingleton().addCompositor(vp,"Compositor_invert");
		Ogre::CompositorManager::getSingleton().addCompositor(vp,"pc_comp");
	}
};

int WINAPI WinMain(HINSTANCE, HINSTANCE, char *,int)
{
	exampleapp obj;
	int ret = obj.startup();
	while(obj.fkeeprunning())
	{
		obj.renderOneFrame();
	}
	return ret;
}