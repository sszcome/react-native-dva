import React from 'react';
import {createBottomTabNavigator, BottomTabBar} from 'react-navigation-tabs';
import {createAppContainer} from 'react-navigation';
import Home from './Home';
import Account from './Account';
import Detail from './Detail';

const TabBarComponent = props => <BottomTabBar {...props} />;

// 动态配置
class DynamicTabBarComponent extends React.PureComponent {

  render(): React.ReactNode {
    let routes = {
      Home: {screen: Home},
      Account: {screen: Account},
    };
    const Tab = createAppContainer(createBottomTabNavigator(routes,{

    }));
    return <Tab />;
  }
}

const TabScreens = createBottomTabNavigator(
  {
    Home: {screen: Home},
    Account: {screen: Account},
    Detail: {screen: Detail},
  },
  {
    tabBarComponent: props => (
      <TabBarComponent {...props} style={{borderTopColor: '#605F60'}} />
    ),
  },
);

export default DynamicTabBarComponent;
