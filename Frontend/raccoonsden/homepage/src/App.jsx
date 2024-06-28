import './App.css'
import { RdCommentCard } from './RdCommentCard'

export function App() {
  const formatUserName = (userName) => `@${userName}`

  return (
    <section className='App'>
      <RdCommentCard formatUserName={formatUserName} initialIsFollowing={false} userName="cesarpro">
          Cesar Eduardo Munoz Rolon
      </RdCommentCard>

      <RdCommentCard formatUserName={formatUserName} initialIsFollowing userName="midudev">
          Midu el dev teacher
      </RdCommentCard>

      <RdCommentCard formatUserName={formatUserName} userName="batman">
          El Brus Wein
      </RdCommentCard>
    </section>
  )
}
