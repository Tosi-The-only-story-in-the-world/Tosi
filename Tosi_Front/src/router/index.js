import { createRouter, createWebHistory } from "vue-router";
import CustomTaleView from '@/views/CustomTaleView.vue'
import CustomTaleCreate from '@/components/customTale/customTaleCreate.vue'
import CustomTaleList from '@/components/customTale/customTaleList.vue'
import CustomTaleDetail from '@/components/customTale/customTaleDetail.vue'
import CustomTaleSave from '@/components/customTale/customTaleSave.vue'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/customTale',
            name: 'customTale',
            component: CustomTaleView,
            children: [
              {
                path: '',
                name: 'customTaleList',
                component: CustomTaleList,
              },
              {
                path: 'create',
                name: 'customTaleCreate',
                component: CustomTaleCreate,
              },
              {
                path: 'save',
                name: 'customTaleSave',
                component: CustomTaleSave,
                props: true,
                // props: route => ({ imageUrl: route.params.imageUrl, gptMessage: route.params.gptMessage })
              },
              {
                path: ':customTale_id',
                name: 'customTaleDetail',
                component: CustomTaleDetail,
              },
            ]
          },
      
    ],
});

export default router;
