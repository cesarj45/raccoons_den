import{ useState } from 'react'

export function RdCommentCard({formatUserName, children, userName = "unknown", initialIsFollowing }){
    const [isFollowing, setIsFollowing] = useState(initialIsFollowing)
    
    const text = isFollowing ? 'Siguiendo' : 'Seguir';
    const buttonClassName = isFollowing 
        ? 'rc-commentCard-button is-following' 
        : 'rc-commentCard-button'

    const handleClick = () => {
        setIsFollowing(!isFollowing)
    }

    return(
        <>
      <article className='rc-commentCard'>
        <header className='rc-commentCard-header'> 
          <img 
            className='rc-commentCard-avatar'  
            src={`https://unavatar.io/${userName}`} />
          <div className='rc-commentCard-info'>
            <strong >{children}</strong>
            <span className='rc-commentCard-infoUserName'>{formatUserName(userName)}</span>
          </div>
        </header>

        <aside>
          <button className={buttonClassName} onClick={handleClick}>
            {text}
          </button>
        </aside>
      </article>
    </>
    )
}